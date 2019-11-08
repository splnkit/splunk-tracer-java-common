package com.splunk.tracer.transport;

public final class Timestamp { 
   
  	private final long seconds_;
    private final int nanoseconds_;

    public Timestamp() {
        seconds_ = 0;
        nanoseconds_ = 0;
    }

    public Timestamp(int sec, int nanos) {
        seconds_ = sec;
        nanoseconds_ = nanos;
    }
	public long getSeconds() {
	    return seconds_;
	}

    public int getNanos() {
        return nanoseconds_;
    }

    public Timestamp(TimestampBuilder builder) 
    { 
        this.seconds_ = builder.seconds_; 
        this.nanoseconds_ = builder.nanoseconds_;
    } 

    public static TimestampBuilder TimestampBuilder() 
    { 
        return new TimestampBuilder(); 
    } 

    // Static class Builder 
    public static class TimestampBuilder { 

        private long seconds_;
        private int nanoseconds_;   
  
        public TimestampBuilder() 
        {
            this.seconds_ = 0;
            this.nanoseconds_ = 0;
        }  
  
        // Setter methods 
        public TimestampBuilder setSeconds(long secs) {
            this.seconds_ = secs;
            return this;
        }

        public TimestampBuilder setNanos(int nanos) {
            this.nanoseconds_ = nanos;
            return this;
        }  
        // build method to deal with outer class 
        // to return outer instance 
        public Timestamp build() 
        { 
            return new Timestamp(this); 
        } 
    } 
  
    @Override
    public String toString() 
    {
        return String.format("%d.%09d", seconds_, nanoseconds_);
    } 
}
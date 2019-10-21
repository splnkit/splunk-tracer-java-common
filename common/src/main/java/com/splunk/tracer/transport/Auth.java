package com.splunk.tracer.transport;

public class Auth {

    private String accessToken_;    //required

    public Auth()
    {
        accessToken_ = "";
    }

    private static final com.splunk.tracer.transport.Auth DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.splunk.tracer.transport.Auth();
    }
    public static com.splunk.tracer.transport.Auth getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    public static AuthBuilder AuthBuilder() 
    { 
        return new AuthBuilder(); 
    } 

    public static class AuthBuilder {

        private String accessToken_;    //required

        public AuthBuilder() 
        { 
        } 

        public AuthBuilder(String token) {
            this.accessToken_ = token;
        }
        public AuthBuilder setAccessToken(String token) {
            this.accessToken_ = token;
            return this;
        }
        public Auth build() {
            // call the private constructor in the outer class
            return new Auth(this);
        }
    }
    private Auth(AuthBuilder builder) {
        this.accessToken_ = builder.accessToken_;
    }
    public String getAccessToken() {
        return accessToken_;
    }
    public void getAccessToken(String token) {
        accessToken_ = token;
    }
}
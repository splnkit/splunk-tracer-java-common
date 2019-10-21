package com.splunk.tracer.transport;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public final class ReportResponse { 
   
  	private final int code_;

    private final String text_;

	public int getCode() {
	    return code_;
	}

    public String getText() {
        return text_;
    }

    public byte[] toByteArray() {
        return new byte[0];
    }

    public static ReportResponseBuilder ReportResponseBuilder() {
        return new ReportResponseBuilder();
    }

    public static class ReportResponseBuilder {

        private int code_;
        private String text_;    //required

        public ReportResponseBuilder() {
        }

        public ReportResponseBuilder(int code, String text) {
            this.code_ = code;
            this.text_ = text;
        }
        public ReportResponseBuilder setCode(int code) {
            this.code_ = code;
            return this;
        }
        public ReportResponseBuilder setText(String text) {
            this.text_ = text;
            return this;
        }
        public ReportResponse build() {
            // call the private constructor in the outer class
            return new ReportResponse(this);
        }
    }
    private ReportResponse(ReportResponseBuilder builder) {
        this.code_ = builder.code_;
        this.text_ = builder.text_;
    }

    public static ReportResponse parseFrom(InputStream data) {
        JSONParser parser = new JSONParser();
        try {
            // String data_string = new String(new byte[data.available()]), "UTF-8");
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(data, "UTF-8"))) { 
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                }
            JSONObject json_obj = (JSONObject) parser.parse(stringBuilder.toString());
            return ReportResponse.ReportResponseBuilder().setCode((int) json_obj.get("code")).setText((String) json_obj.get("text")).build();
        }
        catch(org.json.simple.parser.ParseException e) {
            e.printStackTrace();
            return ReportResponse.ReportResponseBuilder().setCode(100).setText("Response Parsing Error").build();
        }
        catch(java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
            return ReportResponse.ReportResponseBuilder().setCode(101).setText("Response not UTF-8 text").build();
        }  
                        catch (java.io.IOException e)
                {
                    e.printStackTrace();
                    return ReportResponse.ReportResponseBuilder().setCode(103).setText("Conversion Error").build();
                }
    }
}
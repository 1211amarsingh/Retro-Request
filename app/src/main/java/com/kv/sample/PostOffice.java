package com.kv.sample;

import java.util.ArrayList;

public class PostOffice {
    private String Status;
    private String Message;
    private ArrayList<PostModel> PostOffice;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public ArrayList<PostModel> getPostOffice() {
        return PostOffice;
    }

    public void setPostOffice(ArrayList<PostModel> postOffice) {
        PostOffice = postOffice;
    }

    public class PostModel {
        private String Name;
        private String Circle;
        private String District;
        private String State;
        private String Country;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getCircle() {
            return Circle;
        }

        public void setCircle(String circle) {
            Circle = circle;
        }

        public String getDistrict() {
            return District;
        }

        public void setDistrict(String district) {
            District = district;
        }

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }

        public String getCountry() {
            return Country;
        }

        public void setCountry(String country) {
            Country = country;
        }
    }
}

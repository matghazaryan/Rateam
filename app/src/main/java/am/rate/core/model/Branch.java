package am.rate.core.model;

import java.util.List;

public class Branch {
    private int head;
    private String title;
    private String address;
    private double lat;
    private double lng;
    private String contacts;
    private String location;
    private List<WorkDay> workhours;

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public List<WorkDay> getWorkhours() {
        return workhours;
    }

    public void setWorkhours(List<WorkDay> workhours) {
        this.workhours = workhours;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

package data;

public class LocationData {

    Location[] data;

    public LocationData(Location[] data) {
        this.data = data;
    }

    public Location[] getData() {
        return data;
    }

    public void setData(Location[] data) {
        this.data = data;
    }

    public int getSize() {
        return data.length;
    }

}

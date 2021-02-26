package mainpack.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "parcels")
public class ParcelList {

    private List<Parcel> parcelList;

    public ParcelList(){
        parcelList = new ArrayList<Parcel>();
    }

    public ParcelList(List<Parcel> parcelList) {
        this.parcelList = parcelList;
    }

    public void add(Parcel p){
        parcelList.add(p);
    }

    public int size(){
        return parcelList.size();
    }

    @XmlElement(name = "parcel")
    public List<Parcel> getParcelList() {
        return parcelList;
    }

    public void setParcelList(List<Parcel> parcelList) {
        this.parcelList = parcelList;
    }

    @Override
    public String toString() {
        return "ParcelList{" +
                "parcelList=" + parcelList +
                '}';
    }
}

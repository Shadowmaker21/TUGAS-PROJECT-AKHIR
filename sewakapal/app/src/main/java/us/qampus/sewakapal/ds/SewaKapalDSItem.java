
package us.qampus.sewakapal.ds;
import android.graphics.Bitmap;
import buildup.ds.restds.GeoPoint;
import android.net.Uri;

import buildup.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class SewaKapalDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("name") public String name;
    @SerializedName("telp") public String telp;
    @SerializedName("pengguna") public String pengguna;
    @SerializedName("image") public String image;
    @SerializedName("location") public GeoPoint location;
    @SerializedName("id") public String id;
    @SerializedName("imageUri") public transient Uri imageUri;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(telp);
        dest.writeString(pengguna);
        dest.writeString(image);
        dest.writeDoubleArray(location != null  && location.coordinates.length != 0 ? location.coordinates : null);
        dest.writeString(id);
    }

    public static final Creator<SewaKapalDSItem> CREATOR = new Creator<SewaKapalDSItem>() {
        @Override
        public SewaKapalDSItem createFromParcel(Parcel in) {
            SewaKapalDSItem item = new SewaKapalDSItem();

            item.name = in.readString();
            item.telp = in.readString();
            item.pengguna = in.readString();
            item.image = in.readString();
            double[] location_coords = in.createDoubleArray();
            if (location_coords != null)
                item.location = new GeoPoint(location_coords);
            item.id = in.readString();
            return item;
        }

        @Override
        public SewaKapalDSItem[] newArray(int size) {
            return new SewaKapalDSItem[size];
        }
    };

}


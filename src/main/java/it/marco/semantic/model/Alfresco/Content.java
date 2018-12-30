package it.marco.semantic.model.Alfresco;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class Content implements Serializable {

    private final static long serialVersionUID = -9037006040779697180L;
    @SerializedName("mimeType")
    @Expose
    private String mimeType;
    @SerializedName("mimeTypeName")
    @Expose
    private String mimeTypeName;
    @SerializedName("sizeInBytes")
    @Expose
    private int sizeInBytes;
    @SerializedName("encoding")
    @Expose
    private String encoding;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Content withMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public String getMimeTypeName() {
        return mimeTypeName;
    }

    public void setMimeTypeName(String mimeTypeName) {
        this.mimeTypeName = mimeTypeName;
    }

    public Content withMimeTypeName(String mimeTypeName) {
        this.mimeTypeName = mimeTypeName;
        return this;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(int sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public Content withSizeInBytes(int sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
        return this;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Content withEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("mimeType", mimeType).append("mimeTypeName", mimeTypeName).append("sizeInBytes", sizeInBytes).append("encoding", encoding).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(encoding).append(sizeInBytes).append(mimeType).append(mimeTypeName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Content) == false) {
            return false;
        }
        Content rhs = ((Content) other);
        return new EqualsBuilder().append(encoding, rhs.encoding).append(sizeInBytes, rhs.sizeInBytes).append(mimeType, rhs.mimeType).append(mimeTypeName, rhs.mimeTypeName).isEquals();
    }

}

package it.marco.semantic.model.Alfresco;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class CreatedByUser implements Serializable {

    private final static long serialVersionUID = -3358218584749004100L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("displayName")
    @Expose
    private String displayName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CreatedByUser withId(String id) {
        this.id = id;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public CreatedByUser withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("displayName", displayName).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(displayName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CreatedByUser) == false) {
            return false;
        }
        CreatedByUser rhs = ((CreatedByUser) other);
        return new EqualsBuilder().append(id, rhs.id).append(displayName, rhs.displayName).isEquals();
    }

}

package it.marco.semantic.model.Alfresco;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class ModifiedByUser implements Serializable {

    private final static long serialVersionUID = 4481041718496209253L;
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

    public ModifiedByUser withId(String id) {
        this.id = id;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ModifiedByUser withDisplayName(String displayName) {
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
        if ((other instanceof ModifiedByUser) == false) {
            return false;
        }
        ModifiedByUser rhs = ((ModifiedByUser) other);
        return new EqualsBuilder().append(id, rhs.id).append(displayName, rhs.displayName).isEquals();
    }

}

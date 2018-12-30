package it.marco.semantic.model.Alfresco;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

@SuppressWarnings("deprecation")
public class AlfrescoNode implements Serializable {

    private final static long serialVersionUID = -67334454095732070L;
    @SerializedName("entry")
    @Expose
    private Entry entry;

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public AlfrescoNode withEntry(Entry entry) {
        this.entry = entry;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("entry", entry).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(entry).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AlfrescoNode) == false) {
            return false;
        }
        AlfrescoNode rhs = ((AlfrescoNode) other);
        return new EqualsBuilder().append(entry, rhs.entry).isEquals();
    }

}

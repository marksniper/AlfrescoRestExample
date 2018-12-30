package it.marco.semantic.model.Alfresco;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class Entry implements Serializable {

    private final static long serialVersionUID = -2393403473705501132L;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("isFolder")
    @Expose
    private boolean isFolder;
    @SerializedName("isFile")
    @Expose
    private boolean isFile;
    @SerializedName("createdByUser")
    @Expose
    private CreatedByUser createdByUser;
    @SerializedName("modifiedAt")
    @Expose
    private String modifiedAt;
    @SerializedName("modifiedByUser")
    @Expose
    private ModifiedByUser modifiedByUser;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nodeType")
    @Expose
    private String nodeType;
    @SerializedName("content")
    @Expose
    private Content content;
    @SerializedName("parentId")
    @Expose
    private String parentId;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Entry withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public boolean isIsFolder() {
        return isFolder;
    }

    public void setIsFolder(boolean isFolder) {
        this.isFolder = isFolder;
    }

    public Entry withIsFolder(boolean isFolder) {
        this.isFolder = isFolder;
        return this;
    }

    public boolean isIsFile() {
        return isFile;
    }

    public void setIsFile(boolean isFile) {
        this.isFile = isFile;
    }

    public Entry withIsFile(boolean isFile) {
        this.isFile = isFile;
        return this;
    }

    public CreatedByUser getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(CreatedByUser createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Entry withCreatedByUser(CreatedByUser createdByUser) {
        this.createdByUser = createdByUser;
        return this;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Entry withModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public ModifiedByUser getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(ModifiedByUser modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public Entry withModifiedByUser(ModifiedByUser modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Entry withName(String name) {
        this.name = name;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Entry withId(String id) {
        this.id = id;
        return this;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Entry withNodeType(String nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Entry withContent(Content content) {
        this.content = content;
        return this;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Entry withParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("\n").
                append("name", name).append("\n").
                append("id", id).append("\n").
                append("createdAt", createdAt).append("\n").
                append("isFolder", isFolder).append("\n").
                append("isFile", isFile).append("\n").
                append("createdByUser", createdByUser).append("\n").
                append("modifiedAt", modifiedAt).append("\n").
                append("modifiedByUser", modifiedByUser).append("\n").
                append("nodeType", nodeType).append("\n").
                append("content", content).append("\n").
                append("parentId", parentId).append("\n").
                toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(content).append(id).append(parentId).append(modifiedByUser).append(isFolder).append(modifiedAt).append(isFile).append(createdAt).append(name).append(nodeType).append(createdByUser).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Entry) == false) {
            return false;
        }
        Entry rhs = ((Entry) other);
        return new EqualsBuilder().append(content, rhs.content).append(id, rhs.id).append(parentId, rhs.parentId).append(modifiedByUser, rhs.modifiedByUser).append(isFolder, rhs.isFolder).append(modifiedAt, rhs.modifiedAt).append(isFile, rhs.isFile).append(createdAt, rhs.createdAt).append(name, rhs.name).append(nodeType, rhs.nodeType).append(createdByUser, rhs.createdByUser).isEquals();
    }

}

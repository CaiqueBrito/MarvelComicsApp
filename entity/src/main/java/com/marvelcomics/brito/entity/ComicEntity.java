package com.marvelcomics.brito.entity;

public class ComicEntity {

    private int id;
    private String title;
    private String description;
    private ThumbnailEntity thumbnailEntity;

    public ComicEntity(int id, String title, String description, ThumbnailEntity thumbnailEntity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnailEntity = thumbnailEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ThumbnailEntity getThumbnailEntity() {
        return thumbnailEntity;
    }

    public void setThumbnailEntity(ThumbnailEntity thumbnailEntity) {
        this.thumbnailEntity = thumbnailEntity;
    }

    public String getFullUrlThumbnailWithAspect(String aspect) {
        ThumbnailEntity thumb = this.getThumbnailEntity();
        if (aspect.isEmpty()) {
            return thumb.getPath() + "." + thumb.getExtension();
        } else {
            return thumb.getPath() + "/" + aspect + "." + thumb.getExtension();
        }
    }
}

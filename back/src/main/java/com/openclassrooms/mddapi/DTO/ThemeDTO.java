package com.openclassrooms.mddapi.DTO;

public class ThemeDTO {

    private Integer id;
    private String title;
    private String content;
    private Boolean isSubscribed;

    public ThemeDTO(Integer id, String title, String content, boolean isSubscribed) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isSubscribed = isSubscribed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        isSubscribed = subscribed;
    }
}

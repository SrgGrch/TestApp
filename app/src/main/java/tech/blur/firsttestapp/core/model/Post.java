package tech.blur.firsttestapp.core.model;

public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;
    private int priority = 0;
    private int type = 0;

    public Post(int userId, int id, String title, String body, int priority, int type) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        this.priority = priority;
        this.type = type;
    }

    public Post(int userId, int id, String title, String body, int priority) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        this.priority = priority;
    }

    public Post(PostServer post) {
        this.userId = post.getUserId();
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getPriority() {
        return priority;
    }

    public int getType() {
        return type;
    }
}


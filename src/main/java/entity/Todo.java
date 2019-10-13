package entity;

public class Todo {

	private int id, userId;
	private String content;
	private boolean done;

	public Todo() {
	}

	public Todo(String content, boolean done, int userId) {
		this.content = content;
		this.done = done;
		this.userId = userId;
	}

	public Todo(int id, String content, boolean done) {
		this.id = id;
		this.content = content;
		this.done = done;
	}

	public Todo(int id, boolean done) {
		this.id = id;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}

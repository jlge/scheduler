package main;

/**
 *
 * @author joann
 */
public class Task {
    
    private String title;
    private String course;
    private String details;
    
    public Task(String title, String course, String details) {
        this.title = title;
        this.course = course;
        this.details = details;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public String getDetails() {
        return details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
    
    @Override
    public String toString() {
        return "Task [Title=" + title + ", Course=" + course + ", Details=" + details + "]";
    }
            
}

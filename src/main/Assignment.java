package main;

import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Assignment extends Task {
    
    private LocalDate dueDate;
    private boolean status;
    private BooleanProperty selected = new SimpleBooleanProperty();
    
    public Assignment(String title, String course, String details, LocalDate dueDate, boolean status) {
        super(title, course, details);
        this.dueDate = dueDate;
        this.status = status;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    public BooleanProperty getStatusValue() {
        return new SimpleBooleanProperty(status);
    }
       
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public BooleanProperty selectedProperty() {
        return selected ;
    }

    public final boolean isSelected() {
        return selectedProperty().get();
    }

    public final void setSelected(boolean selected) {
        selectedProperty().set(selected);
    }   
    
    @Override
    public String toString() {
        return getCourse() + ": " + getTitle() + " (" + dueDate + ")";
    }    
}

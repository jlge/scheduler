package main;

import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


public class Assessment extends Task {
    
    private LocalDate date;
    private boolean studied;
    private BooleanProperty selected = new SimpleBooleanProperty();
    
    public Assessment(String title, String course, String details, LocalDate date, boolean studied) {
        super(title, course, details);
        this.date = date;
        this.studied = studied;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public boolean getStudied() {
        return studied;
    }
    
    public BooleanProperty getStudiedValue() {
        return new SimpleBooleanProperty(studied);
    }
    
    public void setStudied(boolean studied) {
        this.studied = studied;
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
        return getCourse() + ": " + getTitle() + " (" + date + ")";
    }        
}

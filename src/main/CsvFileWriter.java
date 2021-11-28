package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class CsvFileWriter {
    
    private static final String COMMA_DELIMITER = ",";
    private static final String ACCOUNTS_FILE_HEADER = "name,username,password";
    private static final String ASSESSMENTS_FILE_HEADER = "title,course,details,date,studied";
    private static final String ASSIGNMENTS_FILE_HEADER = "title,course,details,dueDate,status";
            
    /**
     *
     * @param newUser
     */
    public static void addAccount(User newUser) {
        File accountsFile;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        
        try {
            accountsFile = new File("accounts.csv");
            boolean exists = accountsFile.exists();
            
            if (exists) {
                fileWriter = new FileWriter("accounts.csv", true);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.newLine();            
                bufferedWriter.write(newUser.getName() + COMMA_DELIMITER + 
                        newUser.getUsername().toLowerCase() + COMMA_DELIMITER + newUser.getPassword());

                System.out.println("Created account " + newUser.getUsername() + 
                        " successfully.");                
            } else {
                fileWriter = new FileWriter("accounts.csv");
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(ACCOUNTS_FILE_HEADER);
                bufferedWriter.newLine();            
                bufferedWriter.write(newUser.getName() + COMMA_DELIMITER + 
                        newUser.getUsername().toLowerCase() + COMMA_DELIMITER + newUser.getPassword());
                
                System.out.println("Created accounts.csv successfully.");
                System.out.println("Created account " + newUser.getUsername() + " successfully.");
            }
                       
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter.");
            e.printStackTrace();
        } finally {
            
            try {
                fileWriter.flush();
                bufferedWriter.flush();
                fileWriter.close();
                bufferedWriter.close();
                
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();                
            }
        }    
    }
    
    public void deleteAccount() {
        
    }    
      
    public static void addAssessment(User currUser, Assessment assessment) {
        File assessmentsFile;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        ArrayList<Assessment> arraylist;
        
        try {
            String fileName = currUser.getUsername() + "Assessments.csv";
            assessmentsFile = new File(fileName);
            boolean exists = assessmentsFile.exists();
            
            if (exists) {
                arraylist = CsvFileReader.getSortedAssessments(assessment, currUser.getUsername());
                fileWriter = new FileWriter(fileName);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(ASSESSMENTS_FILE_HEADER);
                for (Assessment newAssessment:arraylist) {
                    bufferedWriter.newLine();            
                    bufferedWriter.write(newAssessment.getTitle() + COMMA_DELIMITER + 
                            newAssessment.getCourse() + COMMA_DELIMITER + 
                            newAssessment.getDetails() + COMMA_DELIMITER + 
                            newAssessment.getDate() + COMMA_DELIMITER + 
                            newAssessment.getStudied() + COMMA_DELIMITER);
                }
                System.out.println("Added assessment " + assessment.getTitle() + " successfully.");                
            } else {
                fileWriter = new FileWriter(fileName, true);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(ASSESSMENTS_FILE_HEADER);
                bufferedWriter.newLine();            
                bufferedWriter.write(assessment.getTitle() + COMMA_DELIMITER + 
                        assessment.getCourse() + COMMA_DELIMITER + 
                        assessment.getDetails() + COMMA_DELIMITER + 
                        assessment.getDate() + COMMA_DELIMITER + 
                        assessment.getStudied() + COMMA_DELIMITER);
                
                System.out.println("Created " + fileName + " successfully.");
                System.out.println("Added assessment " + assessment.getTitle() + " successfully.");   
            }
                       
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter.");
            e.printStackTrace();
        } finally {
            
            try {
                fileWriter.flush();
                bufferedWriter.flush();
                fileWriter.close();
                bufferedWriter.close();
                
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter");
                e.printStackTrace();                
            }
        }        
    }
    
    public static void addAssignment(User currUser, Assignment assignment) {
        File assignmentsFile;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        ArrayList<Assignment> arraylist;
                
        try {
            String fileName = currUser.getUsername() + "Assignments.csv";
            assignmentsFile = new File(fileName);
            boolean exists = assignmentsFile.exists();
            
            if (exists) {
                arraylist = CsvFileReader.getSortedAssignments(assignment, currUser.getUsername());
                fileWriter = new FileWriter(fileName);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(ASSIGNMENTS_FILE_HEADER);
                for (Assignment newAssignment:arraylist) {
                    bufferedWriter.newLine();
                    bufferedWriter.write(newAssignment.getTitle() + COMMA_DELIMITER + 
                            newAssignment.getCourse() + COMMA_DELIMITER + 
                            newAssignment.getDetails() + COMMA_DELIMITER + 
                            newAssignment.getDueDate() + COMMA_DELIMITER + 
                            newAssignment.getStatus() + COMMA_DELIMITER);
                }

                System.out.println("Added assignment " + assignment.getTitle() + " successfully.");                
            } else {
                fileWriter = new FileWriter(fileName, true);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(ASSIGNMENTS_FILE_HEADER);
                bufferedWriter.newLine();            
                bufferedWriter.write(assignment.getTitle() + COMMA_DELIMITER + 
                        assignment.getCourse() + COMMA_DELIMITER + 
                        assignment.getDetails() + COMMA_DELIMITER + 
                        assignment.getDueDate() + COMMA_DELIMITER + 
                        assignment.getStatus() + COMMA_DELIMITER);
                
                System.out.println("Created " + fileName + " successfully.");
                System.out.println("Added assignment " + assignment.getTitle() + " successfully.");          
            }
                       
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter.");
            e.printStackTrace();
        } finally {
            
            try {
                fileWriter.flush();
                bufferedWriter.flush();
                fileWriter.close();
                bufferedWriter.close();
                
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();                
            }
        }       
    }
    
    public static void editAssessment(User currUser, Assessment assessment, Assessment editedAssessment) {
        File assessmentsFile;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        ArrayList<Assessment> arraylist;
                
        try {
            String fileName = currUser.getUsername() + "Assessments.csv";
            assessmentsFile = new File(fileName);
            boolean exists = assessmentsFile.exists();
            
            if (exists) {
                arraylist = CsvFileReader.updateAssessment(currUser.getUsername(), assessment, editedAssessment);
                fileWriter = new FileWriter(fileName);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(ASSESSMENTS_FILE_HEADER);
                for (Assessment newAssessment:arraylist) {
                    bufferedWriter.newLine();
                    bufferedWriter.write(newAssessment.getTitle() + COMMA_DELIMITER + 
                            newAssessment.getCourse() + COMMA_DELIMITER + 
                            newAssessment.getDetails() + COMMA_DELIMITER + 
                            newAssessment.getDate() + COMMA_DELIMITER + 
                            newAssessment.getStudied() + COMMA_DELIMITER);
                }

                System.out.println("Added assessment " + assessment.getTitle() + " successfully.");                
            } else {
                fileWriter = new FileWriter(fileName, true);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(ASSESSMENTS_FILE_HEADER);
                bufferedWriter.newLine();            
                bufferedWriter.write(assessment.getTitle() + COMMA_DELIMITER + 
                        assessment.getCourse() + COMMA_DELIMITER + 
                        assessment.getDetails() + COMMA_DELIMITER + 
                        assessment.getDate() + COMMA_DELIMITER + 
                        assessment.getStudied() + COMMA_DELIMITER);
                
                System.out.println("Created " + fileName + " successfully.");
                System.out.println("Added assessment " + assessment.getTitle() + " successfully.");          
            }
                       
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter.");
            e.printStackTrace();
        } finally {
            
            try {
                fileWriter.flush();
                bufferedWriter.flush();
                fileWriter.close();
                bufferedWriter.close();
                
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();                
            }
        }          
    }
    
    public static void editAssignment(User currUser, Assignment assignment, Assignment editedAssignment) {
        File assignmentsFile;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        ArrayList<Assignment> arraylist;
                
        try {
            String fileName = currUser.getUsername() + "Assignments.csv";
            assignmentsFile = new File(fileName);
            boolean exists = assignmentsFile.exists();
            
            if (exists) {
                arraylist = CsvFileReader.updateAssignment(currUser.getUsername(), assignment, editedAssignment);
                fileWriter = new FileWriter(fileName);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(ASSIGNMENTS_FILE_HEADER);
                for (Assignment newAssignment:arraylist) {
                    bufferedWriter.newLine();
                    bufferedWriter.write(newAssignment.getTitle() + COMMA_DELIMITER + 
                            newAssignment.getCourse() + COMMA_DELIMITER + 
                            newAssignment.getDetails() + COMMA_DELIMITER + 
                            newAssignment.getDueDate() + COMMA_DELIMITER + 
                            newAssignment.getStatus() + COMMA_DELIMITER);
                }

                System.out.println("Added assignment " + assignment.getTitle() + " successfully.");                
            } else {
                fileWriter = new FileWriter(fileName, true);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(ASSIGNMENTS_FILE_HEADER);
                bufferedWriter.newLine();            
                bufferedWriter.write(assignment.getTitle() + COMMA_DELIMITER + 
                        assignment.getCourse() + COMMA_DELIMITER + 
                        assignment.getDetails() + COMMA_DELIMITER + 
                        assignment.getDueDate() + COMMA_DELIMITER + 
                        assignment.getStatus() + COMMA_DELIMITER);
                
                System.out.println("Created " + fileName + " successfully.");
                System.out.println("Added assignment " + assignment.getTitle() + " successfully.");          
            }
                       
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter.");
            e.printStackTrace();
        } finally {
            
            try {
                fileWriter.flush();
                bufferedWriter.flush();
                fileWriter.close();
                bufferedWriter.close();
                
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();                
            }
        }        
    }
    
    public void deleteAssessment() {
        
    }
    
    public void deleteAssignment() {
        
    }
    
    
    public static void main(String [] args) {
        User joanna = new User("Joanna", "joannage", "ilikepizza");
        User yuri = new User("Yuri", "yurikim", "ilikejisung");
        LocalDate date = LocalDate.parse("2021-01-06");
        Assignment textbook = new Assignment("Worksheet", "Calculus", "Unit 4 - Derivatives", date, false);
        Assessment quiz = new Assessment("Quiz 3", "Calculus", "Unit 3 - Power Rule", date, false);
        
        addAccount(joanna);
        addAccount(yuri);
        addAssignment(yuri, textbook);
        addAssessment(yuri, quiz);
        addAssessment(joanna, quiz);
        addAssignment(joanna, textbook);
        
    }
    
}

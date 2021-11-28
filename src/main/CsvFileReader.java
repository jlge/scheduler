package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvFileReader {
    
    private static final String COMMA_DELIMITER = ",";
    private static final String ACCOUNT_FILE_NAME = "accounts.csv";
    private static final int NAME_INDEX = 0;
    private static final int USERNAME_INDEX = 1;
    private static final int PASSWORD_INDEX = 2;
    private static final int ASSIGNMENT_DUEDATE = 3;
    
    public static int checkAccountExist(String username) {
        
        BufferedReader fileReader = null;
        String line = "";
        String[] entries;
        boolean found = false;
        int counter = 0;
        
        try {
            fileReader = new BufferedReader(new FileReader(ACCOUNT_FILE_NAME));
            fileReader.readLine();
            
            while ((line = fileReader.readLine()) != null) {
                entries = line.split(COMMA_DELIMITER);
                
                if (entries.length > 0) {
                    if (entries[USERNAME_INDEX].equals(username)) {
                        found = true;
                        break;
                    }
                    counter++;
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader.");
            e.printStackTrace();
        } finally {
            
            try {
                fileReader.close();
                
            } catch (IOException e) {
                System.out.println("Error closing fileReader.");
                e.printStackTrace();                
            }
        }  
        
        if (found) {
            return counter;
        } else {
            return -1;
        }              
    }
    
    public static boolean checkValidLogin(String usernameInput, String passwordInput) {
        int index = checkAccountExist(usernameInput);
        String[] entries;
        String password = "";
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ACCOUNT_FILE_NAME));
            for (int i = 0; i <= index; i++) {
                bufferedReader.readLine();
            }
            entries = bufferedReader.readLine().split(COMMA_DELIMITER);
            password = entries[PASSWORD_INDEX];
            
        } catch(IOException e) {
            System.out.println("Error in CsvFileReader.");
            e.printStackTrace(); 
        }

        if (password.equals(passwordInput)) {
            return true;
        } else {
            return false;
        }
        
     }
    
    public static String getAccountName(String username) {
        int index = checkAccountExist(username);
        String[] entries;
        String name = "";
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ACCOUNT_FILE_NAME));
            for (int i = 0; i <= index; i++) {
                bufferedReader.readLine();
            }
            entries = bufferedReader.readLine().split(COMMA_DELIMITER);
            name = entries[NAME_INDEX];
            
        } catch(IOException e) {
            System.out.println("Error in CsvFileReader.");
            e.printStackTrace(); 
        }
        
        return name;        
    }
    
    public static ArrayList<Assignment> getAllAssignments(String username) {
        BufferedReader reader = null;
        String fileName = username + "Assignments.csv";
        ArrayList<Assignment> arraylist = new ArrayList<Assignment>();
        File assignmentsFile = new File(fileName);
        boolean exists = assignmentsFile.exists();
        
        if (exists) {
            try {           
                reader = new BufferedReader(new FileReader(fileName));
                reader.readLine();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    String[] info = line.split(COMMA_DELIMITER);
                    if (info.length > 0) {
                        if (info[4].equals("false")) {
                            arraylist.add(new Assignment(info[0], info[1], info[2], LocalDate.parse(info[3]), Boolean.parseBoolean(info[4])));
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Error in CsvFileReader");
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch(IOException e) {
                    System.out.println("Error while closing reader");
                    e.printStackTrace();
                }
            }
            
        } 
        return arraylist;
    }
    
    public static ArrayList<Assessment> getAllAssessments(String username) {
        BufferedReader reader = null;
        String fileName = username + "Assessments.csv";
        ArrayList<Assessment> arraylist = new ArrayList<Assessment>();
        File assessmentsFile = new File(fileName);
        boolean exists = assessmentsFile.exists();
        
        if (exists) {        
            try {           
                reader = new BufferedReader(new FileReader(fileName));
                reader.readLine();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    String[] info = line.split(COMMA_DELIMITER);
                    if (info.length > 0) {
                        if (info[4].equals("false")) {
                            arraylist.add(new Assessment(info[0], info[1], info[2], LocalDate.parse(info[3]), Boolean.parseBoolean(info[4])));
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Error in CsvFileReader");
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch(IOException e) {
                    System.out.println("Error while closing reader");
                    e.printStackTrace();
                }
            }
        }
        return arraylist;       
    }
    
    public static ArrayList<Assignment> getSortedAssignments(Assignment assignment, String username) {
        BufferedReader reader = null;
        String fileName = username + "Assignments.csv";
        File assignmentsFile = new File(fileName);
        boolean exists = assignmentsFile.exists();
        boolean added = false;
        ArrayList<Assignment> arraylist = new ArrayList<Assignment>();
        
        try {           
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            String line = "";
            
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(COMMA_DELIMITER);
                if (info.length > 0) {
                    arraylist.add(new Assignment(info[0], info[1], info[2], LocalDate.parse(info[3]), Boolean.parseBoolean(info[4])));
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch(IOException e) {
                System.out.println("Error while closing reader");
                e.printStackTrace();
            }
        }
        
        for (int i = 0; i < arraylist.size(); i++) {
            System.out.println(assignment);
            if (assignment.getDueDate().isBefore(arraylist.get(i).getDueDate())) {
                System.out.println("ADDED ASSIGNMENT BEFORE");
                arraylist.add(i, assignment);
                added = true;
                break;
            }
        }
        
        if (added) {
            return arraylist;
        } else {
            arraylist.add(assignment);
            System.out.println("ADDED ASSIGNMENT AFTER");
            return arraylist;
        }
    }
    
    public static ArrayList<Assessment> getSortedAssessments(Assessment assessment, String username) {
        BufferedReader reader = null;
        String fileName = username + "Assessments.csv";
        File assessmentsFile = new File(fileName);
        boolean exists = assessmentsFile.exists();
        boolean added = false;
        ArrayList<Assessment> arraylist = new ArrayList<Assessment>();
        
        try {           
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            String line = "";
            
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(COMMA_DELIMITER);
                if (info.length > 0) {
                    arraylist.add(new Assessment(info[0], info[1], info[2], LocalDate.parse(info[3]), Boolean.parseBoolean(info[4])));
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch(IOException e) {
                System.out.println("Error while closing reader");
                e.printStackTrace();
            }
        }
        
        for (int i = 0; i < arraylist.size(); i++) {
            if (assessment.getDate().isBefore(arraylist.get(i).getDate())) {
                arraylist.add(i, assessment);
                added = true;
                break;
            }
        }
        
        if (added) {
            return arraylist;
        } else {
            arraylist.add(assessment);
            System.out.println("ADDED ASSIGNMENT AFTER");
            return arraylist;
        }
    }
    
    public static ArrayList<Assignment> getAssignments(String username, LocalDate date) {
        BufferedReader reader = null;
        String fileName = username + "Assignments.csv";
        ArrayList<Assignment> arraylist = new ArrayList<Assignment>();
        File assignmentsFile = new File(fileName);
        boolean exists = assignmentsFile.exists();
        
        if (exists) {
            try {           
                reader = new BufferedReader(new FileReader(fileName));
                reader.readLine();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    String[] info = line.split(COMMA_DELIMITER);
                    if (info.length > 0) {
                        if (info[3].equals(date.toString()) && info[4].equals("false")) {
                            arraylist.add(new Assignment(info[0], info[1], info[2], 
                                    LocalDate.parse(info[3]), Boolean.parseBoolean(info[4])));
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Error in CsvFileReader");
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch(IOException e) {
                    System.out.println("Error while closing reader");
                    e.printStackTrace();
                }
            }
            
        } 
        return arraylist;
    }
    
    public static ArrayList<Assessment> getAssessments(String username, LocalDate date) {
        BufferedReader reader = null;
        String fileName = username + "Assessments.csv";
        ArrayList<Assessment> arraylist = new ArrayList<Assessment>();
        File assessmentsFile = new File(fileName);
        boolean exists = assessmentsFile.exists();
        
        if (exists) {
            try {           
                reader = new BufferedReader(new FileReader(fileName));
                reader.readLine();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    String[] info = line.split(COMMA_DELIMITER);
                    if (info.length > 0) {
                        if (info[3].equals(date.toString()) && info[4].equals("false")) {
                            arraylist.add(new Assessment(info[0], info[1], info[2], LocalDate.parse(info[3]), Boolean.parseBoolean(info[4])));
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Error in CsvFileReader");
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch(IOException e) {
                    System.out.println("Error while closing reader");
                    e.printStackTrace();
                }
            }
            
        } 
        return arraylist;
    }
    
    public static ArrayList<Assignment> updateAssignment(String username, Assignment assignment, Assignment updated) {
        BufferedReader reader = null;
        String fileName = username + "Assignments.csv";
        ArrayList<Assignment> arraylist = new ArrayList<Assignment>();
        File assignmentsFile = new File(fileName);
        boolean exists = assignmentsFile.exists();
        boolean found = false;
        
        if (exists) {
            try {           
                reader = new BufferedReader(new FileReader(fileName));
                reader.readLine();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    String[] info = line.split(COMMA_DELIMITER);
                    if (info.length > 0) {
                        if (found) {
                            arraylist.add(new Assignment(info[0], info[1], info[2], LocalDate.parse(info[3]), Boolean.parseBoolean(info[4])));
                        } else {
                            if (assignment.getTitle().equals(info[0]) && assignment.getCourse().equals(info[1]) &&
                                    assignment.getDetails().equals(info[2]) && assignment.getDueDate().toString().equals(info[3]) && Boolean.toString(assignment.getStatus()).equals(info[4])) {
                                arraylist.add(updated);
                                found = true;
                            }  else {
                                arraylist.add(new Assignment(info[0], info[1], info[2], LocalDate.parse(info[3]), Boolean.parseBoolean(info[4])));
                            }
                        }
                        
                    }
                }

            } catch (Exception e) {
                System.out.println("Error in CsvFileReader");
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch(IOException e) {
                    System.out.println("Error while closing reader");
                    e.printStackTrace();
                }
            }
            
        } 
        return arraylist; 
    }
    
    public static ArrayList<Assessment> updateAssessment(String username, Assessment assessment, Assessment updated) {
        BufferedReader reader = null;
        String fileName = username + "Assessments.csv";
        ArrayList<Assessment> arraylist = new ArrayList<Assessment>();
        File assessmentsFile = new File(fileName);
        boolean exists = assessmentsFile.exists();
        boolean found = false;
        
        if (exists) {
            try {           
                reader = new BufferedReader(new FileReader(fileName));
                reader.readLine();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    String[] info = line.split(COMMA_DELIMITER);
                    if (info.length > 0) {
                        if (found) {
                            arraylist.add(new Assessment(info[0], info[1], info[2], LocalDate.parse(info[3]), Boolean.parseBoolean(info[4])));
                        } else {
                            if (assessment.getTitle().equals(info[0]) && assessment.getCourse().equals(info[1]) &&
                                    assessment.getDetails().equals(info[2]) && assessment.getDate().toString().equals(info[3]) && Boolean.toString(assessment.getStudied()).equals(info[4])) {
                                arraylist.add(updated);
                                found = true;
                            }  else {
                                arraylist.add(new Assessment(info[0], info[1], info[2], LocalDate.parse(info[3]), Boolean.parseBoolean(info[4])));
                            }
                        }
                        
                    }
                }

            } catch (Exception e) {
                System.out.println("Error in CsvFileReader");
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch(IOException e) {
                    System.out.println("Error while closing reader");
                    e.printStackTrace();
                }
            }
            
        } 
        return arraylist; 
    }    
    
    /*
    public static ArrayList<Assessment> getAssessments(YearMonth yearMonth, String username) {
        BufferedReader reader = null;
        String fileName = username + "Assignments.csv";
        ArrayList<Assessment> arraylist = new ArrayList<Assessment>();
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);               
        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        calendarDate = calendarDate.minusDays(1);
        LocalDate calendarEndDate = calendarDate.plusDays(36);
        
        LocalDate assignmentDate;
        
        try {           
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            String line = "";
            
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(COMMA_DELIMITER);
                if (info.length > 0) {
                    assignmentDate = LocalDate.parse(info[ASSIGNMENT_DUEDATE]);
                    System.out.println(assignmentDate);
                    System.out.println(calendarDate);
                    System.out.println(calendarEndDate);
                    if (assignmentDate.isAfter(calendarDate) && assignmentDate.isBefore(calendarEndDate)) {
                        arraylist.add(new Assignment(info[0], info[1], info[2], LocalDate.parse(info[3]), Boolean.parseBoolean(info[4])));
                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch(IOException e) {
                System.out.println("Error while closing reader");
                e.printStackTrace();
            }
        }
        return arraylist;
    }*/
    
    public static ArrayList<ArrayList<String>> getStatistics(User currUser) {
        ArrayList<ArrayList<String>> statistics = new ArrayList<ArrayList<String>>();
        ArrayList<Integer> taskStats = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0));
        ArrayList<String> courses = new ArrayList<String>();
        ArrayList<Integer> courseStats = new ArrayList<Integer>();   
        BufferedReader assignReader = null;
        BufferedReader assessReader = null;
        String assignName = currUser.getUsername() + "Assignments.csv";
        String assessName = currUser.getUsername() + "Assessments.csv";
        
        File assignmentsFile = new File(assignName);
        File assessmentsFile = new File(assessName);
        boolean exists = assignmentsFile.exists();
        
        if (exists) {
            try {           
                assignReader = new BufferedReader(new FileReader(assignName));
                assignReader.readLine();
                String line;
                int index;
                String course;
                String status;

                while ((line = assignReader.readLine()) != null) {
                    String[] info = line.split(COMMA_DELIMITER);
                    if (info.length > 0) {
                        course = info[1];
                        if (courses.contains(course)) {
                            index = courses.indexOf(course);
                            courseStats.set(index, courseStats.get(index) + 1);
                        } else {
                            courses.add(course);
                            courseStats.add(1);
                        }
                        
                        status = info[4];
                        if (status.equals("true")) {
                            taskStats.set(2, taskStats.get(2) + 1);
                        }
                        taskStats.set(0, taskStats.get(0) + 1);
                        taskStats.set(3, taskStats.get(3) + 1);
                    }
                }

            } catch (Exception e) {
                System.out.println("Error in CsvFileReader");
                e.printStackTrace();
            } finally {
                try {
                    assignReader.close();
                } catch(IOException e) {
                    System.out.println("Error while closing reader");
                    e.printStackTrace();
                }
            }
        }
        
        exists = assessmentsFile.exists();
        if (exists) {
            try {           
                assessReader = new BufferedReader(new FileReader(assessName));
                assessReader.readLine();
                String line;
                int index;
                String course;
                String status;


                while ((line = assessReader.readLine()) != null) {
                    String[] info = line.split(COMMA_DELIMITER);
                    if (info.length > 0) {
                        course = info[1];
                        if (courses.contains(course)) {
                            index = courses.indexOf(course);
                            courseStats.set(index, courseStats.get(index) + 1);
                        } else {
                            courses.add(course);
                            courseStats.add(1);
                        }
                        
                        status = info[4];
                        if (status.equals("true")) {
                            taskStats.set(2, taskStats.get(2) + 1);
                        }
                        taskStats.set(1, taskStats.get(1) + 1);
                        taskStats.set(3, taskStats.get(3) + 1);
                    }
                }

            } catch (Exception e) {
                System.out.println("Error in CsvFileReader");
                e.printStackTrace();
            } finally {
                try {
                    assessReader.close();
                } catch(IOException e) {
                    System.out.println("Error while closing reader");
                    e.printStackTrace();
                }
            }
        }
        
        ArrayList<String> stringList = new ArrayList<String>();
        ArrayList<String> stringList2 = new ArrayList<String>();
        taskStats.forEach(integer -> {
            stringList.add(String.valueOf(integer));
        });
        statistics.add(stringList);
        statistics.add(courses);
        courseStats.forEach(integer -> {
            stringList2.add(String.valueOf(integer));        
        });
        statistics.add(stringList2);
        return statistics;
    }
    
    public static void main(String [] args) {
        User jo = new User("jo", "jo", "jo");
        ArrayList<ArrayList<String>> stats = getStatistics(jo);
        ArrayList<String> courseStats = stats.get(2);
        ArrayList<String> taskStats = stats.get(0);
        ArrayList<String> courses = stats.get(1);
        for (int i = 0; i < courses.size(); i++) {
            System.out.println("Course: " + courses.get(i) + "\tNum: " + courseStats.get(i));
        }
        
        for (String item: taskStats)
            System.out.println("taskstat: " + item);
        
        /*User joanna = new User("Joanna", "joannage", "ilikepizza");
        User yuri = new User("Yuri", "yurikim", "ilikejisung");
        User kyle = new User("Kyle", "kyleliao", "ilikesabrina");
        
        //CsvFileWriter.addAccount(joanna);
        //CsvFileWriter.addAccount(yuri);
        //CsvFileWriter.addAccount(kyle);
        LocalDate localdate = LocalDate.parse("2020-01-06");
        Assignment item = new Assignment("Worksheet", "Calculus", "Unit 4 - Derivatives", localdate, false);
        CsvFileWriter.addAssignment(yuri, item);
        localdate = LocalDate.parse("2021-01-01");
        item.setDueDate(localdate);
        CsvFileWriter.addAssignment(yuri, item);
        localdate = LocalDate.parse("2021-01-30");
        item.setDueDate(localdate);
        CsvFileWriter.addAssignment(yuri, item);
        localdate = LocalDate.parse("2022-01-09");
        item.setDueDate(localdate);
        CsvFileWriter.addAssignment(yuri, item);
        
        System.out.println(checkAccountExist("joannage"));
        System.out.println(checkAccountExist("kyleliao"));
        System.out.println(checkAccountExist("yiyili"));
        
        System.out.println(checkValidLogin(joanna.getUsername(), joanna.getPassword()));
        System.out.println(checkValidLogin(joanna.getUsername(), "peepee"));
        
        System.out.println(getAccountName(kyle.getUsername()));
        YearMonth date = YearMonth.of(2021, 1);
        ArrayList<Assignment> assignments = getAllAssignments("yurikim");
        for (Assignment task:assignments)
            System.out.println(task);
        */
        
    }
}

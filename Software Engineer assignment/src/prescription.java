import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private ArrayList<String> postRemarks; // List of remarks for the prescription

    public prescription(int prescID, String firstName, String lastName, String address, float sphere, float axis, float cylinder, Date examinationDate, String optometrist) {
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
        this.postRemarks = new ArrayList<>(); // Initialize the remarks list
    }

    /**
     * Method to add a prescription. It checks all conditions and returns true if the prescription is valid.
     * Additionally, it prints the file path where the data is written.
     * 
     * @return boolean - true if the prescription meets all conditions, false otherwise.
     */
    public boolean addPrescription() {
        // Condition 1: First name and Last name must be between 4 and 15 characters
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false;
        }

        // Condition 2: Address must have a minimum of 20 characters
        if (address.length() < 20) {
            return false;
        }

        // Condition 3: Sphere should be between -20.00 and +20.00, Cylinder between -4.00 and +4.00, Axis between 0 and 180
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            return false;
        }

        // Condition 4: Optometrist name must be between 8 and 25 characters
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }

        // All conditions are met, write prescription data to the file
        File file = new File("prescription.txt");
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("Name: " + firstName + " " + lastName + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Sphere: " + sphere + ", Cylinder: " + cylinder + ", Axis: " + axis + "\n");
            writer.write("Examination Date: " + examinationDate + "\n");
            writer.write("Optometrist: " + optometrist + "\n");
            writer.write("-----------------------------------------------------\n");

            // Print the file path after writing
            System.out.println("Prescription written to: " + file.getAbsolutePath());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to add a remark to a prescription and write it to a file.
     * It prints the file path where the data is written.
     * 
     * @param remark The text of the remark.
     * @param type The type of the remark ("client" or "optometrist").
     * @return boolean - true if the remark is valid and written to the file, false otherwise.
     */
    public boolean addRemark(String remark, String type) {
        // Condition 1: Remark must have between 6 and 20 words, and start with an uppercase letter
        String[] words = remark.trim().split("\\s+");
        if (words.length < 6 || words.length > 20 || !Character.isUpperCase(words[0].charAt(0))) {
            return false; // Validation failed for remark text
        }

        // Condition 2: Remark type must be either "client" or "optometrist"
        if (!type.equalsIgnoreCase("client") && !type.equalsIgnoreCase("optometrist")) {
            return false; // Validation failed for remark type
        }

        // Condition 3: A prescription can have at most 2 remarks
        if (postRemarks.size() >= 2) {
            return false; // Validation failed because the maximum number of remarks has been reached
        }

        // Add the remark to the list if all conditions are met
        postRemarks.add(remark);

        // Write the valid remark to the file
        File file = new File("remarks.txt");
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("Remark Type: " + type + "\n");
            writer.write("Remark: " + remark + "\n");
            writer.write("-----------------------------------------------------\n");

            // Print the absolute file path after writing
            System.out.println("Remark written to: " + file.getAbsolutePath());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

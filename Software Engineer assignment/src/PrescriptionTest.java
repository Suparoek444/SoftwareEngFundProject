import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionTest {

    private prescription p;

    @BeforeEach
    public void setup() {
        // Initialize a valid prescription before each test
        p = new prescription(1, "John", "Doe", "123 Long Street, SomeCity, 12345", 
                             -2.00f, 45f, -1.50f, new Date(), "Dr. Optic");
    }

    // Tests for addPrescription() Function
    
    // Test for valid prescriptions
    @Test
    public void testValidPrescriptions() {
        // Data1: Valid prescription
        prescription p1 = new prescription(1, "Abcd", "Efgh", "123 Abcd Street, Efg", 
                                           -20.00f, 0f, -4.00f, new Date(), "Dr.Optic");
        assertTrue(p1.addPrescription(), "Data1 should be valid");

        // Data2: Valid prescription with boundary values
        prescription p2 = new prescription(2, "Abcdefghijklmno", "Onmlkjihgfedcba", 
                                           "123 Abcd Street, Efgh", 20.00f, 180f, 4.00f, 
                                           new Date(), "Dr.OpticalextraordinaryXO");
        assertTrue(p2.addPrescription(), "Data2 should be valid");
    }

    // Test for invalid prescriptions
    @Test
    public void testInvalidPrescriptions() {
        // Data1: Multiple invalid fields (invalid name, sphere, etc.)
        prescription p1 = new prescription(1, "Abc", "Efg", "123 Abcd Street Abw", 
                                           -20.10f, -1f, -4.10f, new Date(), "Dr.Opti");
        assertFalse(p1.addPrescription(), "Data1 should be invalid");

        // Data2: Multiple invalid fields (name, address, sphere, cylinder, etc.)
        prescription p2 = new prescription(2, "Abcdefghijklmnop", "Ponmlkjihgfedcba", 
                                           "123 Abcd Street", 20.10f, 181f, 4.10f, 
                                           new Date(), "Dr.OpticalextraordinaryXOX");
        assertFalse(p2.addPrescription(), "Data2 should be invalid");
    }

    // Test for invalid sphere case
    @Test
    public void testInvalidSphere() {
        // Data1: Invalid Sphere
        prescription p1 = new prescription(1, "Abcd", "Efgh", "123 Abcd Street, Efg", 
                                           -20.10f, 0f, -4.00f, new Date(), "Dr.Optic");
        assertFalse(p1.addPrescription(), "Data1 should be invalid due to sphere");

        // Data2: Invalid Sphere
        prescription p2 = new prescription(2, "Abcdefghijklmno", "Onmlkjihgfedcba", 
                                           "123 Abcd Street, Efgh", 20.10f, 180f, 4.00f, 
                                           new Date(), "Dr.OpticalextraordinaryXO");
        assertFalse(p2.addPrescription(), "Data2 should be invalid due to sphere");
    }

    // Test for invalid name case
    @Test
    public void testInvalidName() {
        // Data1: Invalid name
        prescription p1 = new prescription(1, "Abc", "Efg", "123 Abcd Street, Efg", 
                                           -20.00f, 0f, -4.00f, new Date(), "Dr.Optic");
        assertFalse(p1.addPrescription(), "Data1 should be invalid due to name");

        // Data2: Invalid name
        prescription p2 = new prescription(2, "Abcdefghijklmnop", "Ponmlkjihgfedcba", 
                                           "123 Abcd Street, Efgh", 20.00f, 180f, 4.00f, 
                                           new Date(), "Dr.OpticalextraordinaryXO");
        assertFalse(p2.addPrescription(), "Data2 should be invalid due to name");
    }

    // Test for invalid cylinder case
    @Test
    public void testInvalidCylinder() {
        // Data1: Invalid Cylinder
        prescription p1 = new prescription(1, "Abcd", "Efgh", "123 Abcd Street, Efg", 
                                           -20.00f, 0f, -4.10f, new Date(), "Dr.Optic");
        assertFalse(p1.addPrescription(), "Data1 should be invalid due to cylinder");

        // Data2: Invalid Cylinder
        prescription p2 = new prescription(2, "Abcdefghijklmno", "Onmlkjihgfedcba", 
                                           "123 Abcd Street, Efgh", 20.00f, 180f, 4.10f, 
                                           new Date(), "Dr.OpticalextraordinaryXO");
        assertFalse(p2.addPrescription(), "Data2 should be invalid due to cylinder");
    }

    // Test for invalid axis case
    @Test
    public void testInvalidAxis() {
        // Data1: Invalid Axis
        prescription p1 = new prescription(1, "Abcd", "Efgh", "123 Abcd Street, Efg", 
                                           -20.00f, -1f, -4.00f, new Date(), "Dr.Optic");
        assertFalse(p1.addPrescription(), "Data1 should be invalid due to axis");

        // Data2: Invalid Axis
        prescription p2 = new prescription(2, "Abcdefghijklmno", "Onmlkjihgfedcba", 
                                           "123 Abcd Street, Efgh", 20.00f, 181f, 4.00f, 
                                           new Date(), "Dr.OpticalextraordinaryXO");
        assertFalse(p2.addPrescription(), "Data2 should be invalid due to axis");
    }

    // Tests for addRemark() Function
    
    // Test for valid remarks
    @Test
    public void testValidRemarks() {
        // Test Data 1: 6 words, 1 remark
        assertTrue(p.addRemark("This is a valid client remark.", "client"), "First remark should be valid");
        
        // Test Data 2: 20 words, 2 remarks
        assertTrue(p.addRemark("This is a valid optometrist remark that contains exactly twenty words for testing purpose.", "optometrist"), 
                   "Second remark with 20 words should be valid");
    }

    // Test for invalid remarks with too few and too many words combined
    @Test
    public void testInvalidWords() {
        // Test Data 1: 5 words, invalid (too few)
        assertFalse(p.addRemark("This is too short.", "client"), "Remark should be invalid (5 words)");

        // Test Data 2: 21 words, invalid (too many)
        assertFalse(p.addRemark("This is a test remark that contains more than the allowed number of words which is over twenty and should fail.", "client"), 
                    "Remark should be invalid (21 words)");
    }

    // Test for invalid remarks with no uppercase start
    @Test
    public void testInvalidNoUpperCase() {
        // Test Data 1: 6 words, no uppercase start
        assertFalse(p.addRemark("this should start with an uppercase.", "client"), 
                    "Remark should be invalid (no uppercase)");

        // Test Data 2: 7 words, no uppercase start
        assertFalse(p.addRemark("invalid start of sentence here.", "optometrist"), 
                    "Remark should be invalid (no uppercase)");
    }

    // Test for invalid remark type
    @Test
    public void testInvalidType() {
        // Test Data 1: Wrong type
        assertFalse(p.addRemark("Valid but wrong type.", "wrongType"), 
                    "Remark should be invalid (wrong type)");

        // Test Data 2: Wrong type
        assertFalse(p.addRemark("This is a valid remark with invalid type.", "other"), 
                    "Remark should be invalid (wrong type)");
    }

    // Test for exceeding the maximum number of remarks (valid remarks first, then exceeding)
    @Test
    public void testMaxRemarksExceeded() {
        // Add two valid remarks first
        assertTrue(p.addRemark("This is a valid client remark.", "client"), "First remark should be valid");
        assertTrue(p.addRemark("This is a valid optometrist remark.", "optometrist"), "Second remark should be valid");

        // Test Data: Adding a 3rd valid remark (invalid due to exceeding limit)
        assertFalse(p.addRemark("This valid remark should fail because it's the third one.", "client"), 
                    "Third valid remark should be invalid (max 2 remarks)");
    }

    // Test for fully invalid cases
    @Test
    public void testInvalidCases() {
        // Test Data 1: Invalid type and too few words
        assertFalse(p.addRemark("Too short.", "wrongType"), "Remark should be invalid (wrong type and too few words)");

        // Test Data 2: No uppercase start and too many words
        assertFalse(p.addRemark("this remark contains more than the allowed number of words and fails.", "client"), 
                    "Remark should be invalid (no uppercase and too many words)");
    }
}

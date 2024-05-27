import java.sql.*;
import java.util.Scanner;
import java.util.HashMap;

public class Main {

    static final String jdbcURL = "";

    public static void main(String[] args) {
        try {

            Class.forName("org.mariadb.jdbc.Driver");

            String user = "";
            String passwd = "";
            boolean x = true;
            boolean inner = true;

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {

                conn = DriverManager.getConnection(jdbcURL, user, passwd); // Create connection instance
                stmt = conn.createStatement();
                String data = "";
                char task;
                String operation;
                Scanner input = new Scanner(System.in);
                HashMap<String, Integer> citationFees = new HashMap<String, Integer>(); // map to store citation fee and
                                                                                        // category mapping
                citationFees.put("Invalid Permit", 25);
                citationFees.put("Expired Permit", 30);
                citationFees.put("No Permit", 40);
                do {
                    System.out.println(
                            "Choose an task:\n 1.Information processing\n 2.Maintaining permits \n 3.Generating and maintaining citations\n 4.Reports");
                    task = input.next().charAt(0);
                    input.nextLine();
                    // Switch case for different tasks
                    switch (task) {
                        case '1':
                            inner = true;
                            do {
                                System.out.println("Choose an operation:\n " + "1) Enter driver details\n"
                                        + "2) Update driver details\n" + "3) Delete driver details\n" +
                                        "4) Enter parking info\n" + "5) Update parking info\n"
                                        + "6) Delete parking info\n" +
                                        "7) Enter permit details\n" + "8) Update permit details\n"
                                        + "9) Delete permit details\n");
                                operation = input.nextLine();
                                // Switch case for different sub operation in a task
                                // Enter Driver Details
                                switch (operation) {
                                    case "1":
                                        inner = true;
                                        System.out.print("Enter DriverID: ");
                                        String driverid = input.nextLine();
                                        System.out.print("Enter name: ");
                                        String name = input.nextLine();
                                        System.out.print("Enter your status: ");

                                        String status = input.nextLine();
                                        // Insert query for inserting driverinformation
                                        stmt.executeUpdate("INSERT INTO DriverInformation (DriverID, Name, Status," +
                                                " NumberOfPermits)" +
                                                " VALUES ('" + driverid + "','" + name + "','" + status + "'," + 0
                                                + " )");
                                        System.out.println("New driver details inserted!\n");
                                        break;

                                    case "2":
                                        inner = true;
                                        // Take all the updated fields as user inputs
                                        System.out.print("Enter driverId to be updated: ");
                                        String updateDriverID = input.nextLine();
                                        System.out.print("Enter name: ");
                                        String updatedname = input.nextLine();
                                        System.out.print("Enter your status: ");
                                        String updatedstatus = input.nextLine();
                                        System.out.print("Enter your NumberOfPermits: ");
                                        String updatedpermit = input.nextLine();
                                        // Update query to updated Driver Information
                                        stmt.executeUpdate("UPDATE DriverInformation SET Name='" + updatedname
                                                + "', NumberOfPermits = '" + updatedpermit + "' , Status ='"
                                                + updatedstatus + "'" +
                                                " WHERE DriverID ='" + updateDriverID + "'");
                                        System.out.println("Updated existing driver details.\n");
                                        break;

                                    case "3":
                                        inner = true;
                                        System.out.print("Enter driverId to be deleted: ");
                                        String deleteDriverID = input.nextLine();
                                        stmt.executeUpdate("DELETE FROM DriverInformation WHERE DriverID ='" +
                                                deleteDriverID + "'");
                                        System.out.println("Deleted existing driver details.\n");
                                        break;

                                    case "4":
                                        inner = true;
                                        System.out.print("Enter Parking Name: ");
                                        String parkingName = input.nextLine();
                                        System.out.print("Enter Zone ID: ");
                                        String zoneId = input.nextLine();
                                        System.out.print("Enter your space number: ");
                                        String spaceNumber = input.nextLine();
                                        System.out.print("Enter your address: ");
                                        String address = input.nextLine();
                                        System.out.print("Enter your space type: ");
                                        String spacetype = input.nextLine();
                                        stmt.executeUpdate(
                                                "INSERT INTO ParkingLotInformation  (Name , ZoneID, SpaceNumber ," +
                                                        " Address , SpaceType, AvailabilityStatus)" +
                                                        " VALUES ('" + parkingName + "' ,'" + zoneId + "' ,'"
                                                        + spaceNumber + "' ,'" + address + "' ,'" + spacetype
                                                        + "' ,'yes')");
                                        System.out.println("Parking details added.\n");
                                        break;

                                    case "5":
                                        inner = true;
                                        System.out.print("Enter parking lot name to be updated: ");
                                        String updateParkingName = input.nextLine();
                                        System.out.print("Enter zone: ");
                                        String updatedZoneId = input.nextLine();
                                        System.out.print("Enter space number: ");
                                        String updatedSpaceNumber = input.nextLine();
                                        System.out.print("Enter lot address: ");
                                        String updatedAddress = input.nextLine();
                                        System.out.print("Enter space type: ");
                                        String updatedSpaceType = input.nextLine();
                                        stmt.executeUpdate(
                                                "UPDATE ParkingLotInformation SET Address='" + updatedAddress +
                                                        "',SpaceType = '" + updatedSpaceType + "' WHERE Name ='"
                                                        + updateParkingName + "' and ZoneID ='" + updatedZoneId
                                                        + "'  and SpaceNumber ='" + updatedSpaceNumber + "'");
                                        System.out.println("Parking details updated. \n");
                                        break;

                                    case "6":
                                        inner = true;
                                        System.out.print("Enter parking lot name to be deleted: ");
                                        String deleteParkingName = input.nextLine();
                                        System.out.print("Enter zone: ");
                                        String deleteZoneId = input.nextLine();
                                        System.out.print("Enter space number: ");
                                        String deleteSpaceNumber = input.nextLine();
                                        stmt.executeUpdate("DELETE FROM ParkingLotInformation WHERE Name ='" +
                                                deleteParkingName + "'  and ZoneID ='" + deleteZoneId
                                                + "'  and SpaceNumber ='" + deleteSpaceNumber + "'");
                                        System.out.println("Deleted existing parking details. \n");
                                        break;

                                    case "7":
                                        inner = true;
                                        System.out.print("Enter permitID: ");
                                        String permitId = input.nextLine();
                                        System.out.print("Enter parkingName: ");
                                        String parkingname = input.nextLine();
                                        System.out.print("Enter zone id: ");
                                        String zoneID = input.nextLine();
                                        System.out.print("Enter space number: ");
                                        String spacenumber = input.nextLine();
                                        System.out.print("Enter driverID: ");
                                        String driverId = input.nextLine();
                                        System.out.print("Enter permit start date: ");
                                        String startdate = input.nextLine();
                                        System.out.print("Enter permit end date: ");
                                        String enddate = input.nextLine();
                                        System.out.print("Enter permit end time: ");
                                        String endtime = input.nextLine();
                                        System.out.print("Enter permit type:");
                                        String permittype = input.nextLine();
                                        System.out.print("Enter vehicle license: ");
                                        String license = input.nextLine();

                                        try {
                                            // Both the queries should be running as a single transaction
                                            conn.setAutoCommit(false);
                                            // Set autocommit to false, so that the queries are not commited after
                                            // execution
                                            stmt.executeUpdate(
                                                    "INSERT INTO PermitInfo (PermitID  , ParkingName , ZoneID ,SpaceNumber ,"
                                                            +
                                                            " DriverID , StartDate  , ExpirationDate , ExpirationTime , PermitType ,VehicleLicense )"
                                                            +
                                                            " VALUES ('" + permitId + "' ,'" + parkingname + "' ,'"
                                                            + zoneID + "' ,'" + spacenumber + "' ,'" + driverId + "' ,'"
                                                            + startdate + "' ,'" + enddate + "' ,'" + endtime + "' ,'"
                                                            + permittype + "' ,'" + license + "' )");
                                            stmt.executeUpdate(
                                                    "UPDATE ParkingLotInformation SET AvailabilityStatus = False WHERE Name ='"
                                                            + parkingname + "' and ZoneID ='" + zoneID
                                                            + "'  and SpaceNumber =' " + spacenumber + "'");
                                            conn.commit();
                                            // Commiting after both the queries have executed.
                                            System.out.println("Permit added successfully.");

                                        } catch (SQLException e) {
                                            // Print the SQl exception
                                            System.out.println(e);
                                            conn.rollback();
                                        } finally {
                                            conn.setAutoCommit(true);
                                            // Set autocommit to true, that is the default option.
                                        }
                                        break;

                                    case "8":
                                        inner = true;
                                        System.out.print("Enter permitID: ");
                                        String updatedpermitId = input.nextLine();
                                        System.out.print("Enter parkingName: ");
                                        String updatedparkingname = input.nextLine();
                                        System.out.print("Enter zone id: ");
                                        String updatedzoneID = input.nextLine();
                                        System.out.print("Enter space number: ");
                                        String updatedspacenumber = input.nextLine();
                                        System.out.print("Enter driverID: ");
                                        String updateddriverId = input.nextLine();
                                        System.out.print("Enter permit start date: ");
                                        String updatedstartdate = input.nextLine();
                                        System.out.print("Enter permit end date: ");
                                        String updatedenddate = input.nextLine();
                                        System.out.print("Enter permit end time: ");
                                        String updatedendtime = input.nextLine();
                                        System.out.print("Enter permit type:");
                                        String updatedpermittype = input.nextLine();
                                        System.out.print("Enter vehicle license: ");
                                        String updatedlicense = input.nextLine();
                                        stmt.executeUpdate("UPDATE PermitInfo SET ParkingName='" + updatedparkingname +
                                                "',ZoneID = '" + updatedzoneID + "' ,SpaceNumber = '"
                                                + updatedspacenumber +
                                                "' ,DriverID = '" + updateddriverId + "' ,StartDate = '"
                                                + updatedstartdate +
                                                "' ,ExpirationDate = '" + updatedenddate + "' ,ExpirationTime = '"
                                                + updatedendtime +
                                                "' ,PermitType = '" + updatedpermittype + "' ,VehicleLicense = '"
                                                + updatedlicense + "' WHERE PermitID ='" +
                                                updatedpermitId + "'");
                                        System.out.println("Updated Successfully!");
                                        break;

                                    case "9":
                                        inner = true;
                                        System.out.print("Enter permitId to be deleted: ");
                                        String deletePermitId = input.nextLine();
                                        stmt.executeUpdate("DELETE FROM PermitInfo WHERE PermitID ='" +
                                                deletePermitId + "'");
                                        System.out.println("Deleted Successfully!");
                                        break;

                                    default:
                                        System.out.println("Invalid Operation!");
                                        inner = false;
                                        break;
                                }
                            } while (inner == true);
                            break;

                        case '2':
                            inner = true;
                            do {
                                System.out.println("Choose an operation:\n " + "1) Check and Enter permit details\n"
                                        + "2) Update permit details\n" +
                                        "3) Add vehicle info\n" + "4) Delete Vehicle info\n");
                                operation = input.nextLine();

                                switch (operation) {
                                    case "1":
                                        inner = true;
                                        System.out.println("Enter DriverId for permit:\n");

                                        String DriverIdPermit = input.nextLine();
                                        System.out.println("Enter Permit Type:\n");

                                        String permitType = input.nextLine();
                                        rs = stmt.executeQuery(
                                                "Select Status, NumberOfPermits from DriverInformation WHERE DriverID ='"
                                                        +
                                                        DriverIdPermit + "'");
                                        String tempStatus = "";
                                        int tempNum = 0;
                                        while (rs.next()) {
                                            tempStatus = rs.getString(1);
                                            tempNum = rs.getInt(2);

                                        }
                                        if ((tempStatus.equals("S") && tempNum < 1
                                                && (!permitType.equals("Special Event")
                                                        || !permitType.equals("Park & Ride")))
                                                || (tempStatus.equals("S") && tempNum < 2
                                                        && (permitType.equals("Special Event")
                                                                || permitType.equals("Park & Ride")))
                                                || (tempStatus.equals("V") && tempNum < 1)
                                                || (tempStatus.equals("E") && (permitType.equals("Special Event")
                                                        || permitType.equals("Park & Ride")) && tempNum < 3)
                                                || (tempStatus.equals("E") && (!permitType.equals("Special Event")
                                                        || !permitType.equals("Park & Ride")) && tempNum < 2)) {
                                            // Both the queries should be running as a single transaction
                                            tempNum++;
                                            try {
                                                conn.setAutoCommit(false);
                                                stmt.executeUpdate("UPDATE DriverInformation SET NumberOfPermits='"
                                                        + Integer.toString(tempNum) + "'");
                                                System.out.print("Enter PermitID: ");
                                                String PermitID = input.nextLine();
                                                System.out.print("Enter ParkingName: ");
                                                String ParkingName = input.nextLine();
                                                System.out.print("Enter ZoneID: ");
                                                String ZoneID = input.nextLine();
                                                System.out.print("Enter SpaceNumber: ");
                                                String SpaceNumber = input.nextLine();
                                                String DriverID = DriverIdPermit;
                                                System.out.print("Enter StartDate: ");
                                                String StartDate = input.nextLine();
                                                System.out.print("Enter ExpirationDate: ");
                                                String ExpirationDate = input.nextLine();
                                                System.out.print("Enter ExpirationTime: ");
                                                String ExpirationTime = input.nextLine();
                                                String PermitType = permitType;
                                                System.out.print("Enter VehicleLicense: ");
                                                String VehicleLicense = input.nextLine();

                                                stmt.executeUpdate(
                                                        "INSERT INTO PermitInfo  (PermitID , ParkingName, ZoneID, SpaceNumber, DriverID, StartDate, ExpirationDate, ExpirationTime, PermitType, VehicleLicense)"
                                                                +
                                                                " VALUES ('" + PermitID + "' ,'" + ParkingName + "' ,'"
                                                                + ZoneID + "' ,'" + SpaceNumber + "' ,'" + DriverID
                                                                + "' ,'" + StartDate + "' ,'" + ExpirationDate + "' ,'"
                                                                + ExpirationTime + "' ,'" + PermitType + "' ,'"
                                                                + VehicleLicense + "' )");
                                                conn.commit();
                                            } catch (SQLException e) {
                                                System.out.println(e);
                                                conn.rollback();
                                            } finally {
                                                conn.setAutoCommit(true);
                                            }
                                            System.out.println("Permit Granted!");
                                            break;

                                        } else {
                                            System.out.println("Exceeded maximum number of limits!");
                                        }
                                        break;

                                    case "2":
                                        inner = true;
                                        System.out.print("Enter permitID: ");
                                        String updatedpermitId = input.nextLine();
                                        System.out.print("Enter ParkingName: ");
                                        String updatedparkingname = input.nextLine();
                                        System.out.print("Enter zone id: ");
                                        String updatedzoneID = input.nextLine();
                                        System.out.print("Enter space number: ");
                                        String updatedspacenumber = input.nextLine();
                                        System.out.print("Enter driverID: ");
                                        String updateddriverId = input.nextLine();
                                        System.out.print("Enter permit start date: ");
                                        String updatedstartdate = input.nextLine();
                                        System.out.print("Enter permit end date: ");
                                        String updatedenddate = input.nextLine();
                                        System.out.print("Enter permit end time: ");
                                        String updatedendtime = input.nextLine();
                                        System.out.print("Enter permit type: ");
                                        String updatedpermittype = input.nextLine();
                                        System.out.print("Enter vehicle license: ");
                                        String updatedlicense = input.nextLine();
                                        stmt.executeUpdate("UPDATE PermitInfo SET ParkingName='" + updatedparkingname +
                                                "',ZoneID = '" + updatedzoneID + "' ,SpaceNumber = '"
                                                + updatedspacenumber +
                                                "',DriverID = '" + updateddriverId + "' ,StartDate = '"
                                                + updatedstartdate +
                                                "',ExpirationDate = '" + updatedenddate + "' ,ExpirationTime = '"
                                                + updatedendtime +
                                                "',PermitType = '" + updatedpermittype + "' ,VehicleLicense = '"
                                                + updatedlicense + "' WHERE PermitID ='" +
                                                updatedpermitId + "'");
                                        System.out.println("Updated Permit Details!");
                                        break;

                                    case "3":
                                        inner = true;
                                        System.out.print("Enter driverID: ");
                                        String DriverIDins = input.nextLine();
                                        System.out.print("Enter VehicleLicense: ");
                                        String VehicleLicenseins = input.nextLine();
                                        System.out.print("Enter Model: ");
                                        String Model = input.nextLine();
                                        System.out.print("Enter Manufacturer: ");
                                        String Manufacturer = input.nextLine();
                                        System.out.print("Enter Color: ");
                                        String Color = input.nextLine();
                                        System.out.print("Enter Year: ");
                                        String Year = input.nextLine();

                                        stmt.executeUpdate(
                                                "INSERT INTO VehicleInfo  (VehicleLicense , Model, Color, Manufacturer, Year, DriverID) "
                                                        +
                                                        "VALUES ('" + VehicleLicenseins + "' ,'" + Model + "' ,'"
                                                        + Color + "' ,'" + Manufacturer + "' ,'" + Year + "' ,'"
                                                        + DriverIDins + "' )");
                                        System.out.println("Vehicle Added Successfully!");
                                        break;

                                    case "4":
                                        inner = true;
                                        System.out.print("Enter the Vehicle license number of vehicle to be deleted: ");
                                        String VehicleLicensedel = input.nextLine();
                                        stmt.executeUpdate("DELETE FROM VehicleInfo WHERE VehicleLicense ='"
                                                + VehicleLicensedel + "'");
                                        System.out.println("Vehicle deleted Successfully! \n");
                                        break;

                                    default:
                                        System.out.println("Invalid Operation! Back to Main Menu. \n");
                                        inner = false;
                                        break;
                                }
                            } while (inner == true);
                            break;
                        case '3':
                            inner = true;
                            do {
                                System.out.println("Choose an operation:\n " +
                                        "1) Detect Violation and Generate Citation\n" + "2) Pay Citation\n"
                                        + "3) Appeal Citation\n");
                                operation = input.nextLine();

                                switch (operation) {
                                    case "1":
                                        inner = true;

                                        System.out.print("Enter Vehicle License Number: ");
                                        String VehicleLicense = input.nextLine();
                                        System.out.print("Enter Parking Lot Name: ");
                                        String ParkingName = input.nextLine();
                                        System.out.print("Enter ZoneID: ");
                                        String ZoneID = input.nextLine();
                                        System.out.print("Enter SpaceNumber: ");
                                        String SpaceNumber = input.nextLine();
                                        rs = stmt.executeQuery(
                                                "SELECT VehicleLicense, ParkingName, ZoneID, SpaceNumber From PermitInfo"
                                                        +
                                                        " Where VehicleLicense ='" + VehicleLicense + "'");
                                        String VehicleLicense_data = "";
                                        String ParkingName_data = "";
                                        String ZoneID_data = "";
                                        String SpaceNumber_data = "";
                                        while (rs.next()) {
                                            VehicleLicense_data = rs.getString(1);
                                            ParkingName_data = rs.getString(2);
                                            ZoneID_data = rs.getString(3);
                                            SpaceNumber_data = rs.getString(4);
                                        }
                                        if (VehicleLicense.equals(VehicleLicense_data)
                                                && ParkingName.equals(ParkingName_data) && ZoneID.equals(ZoneID_data)
                                                && SpaceNumber.equals(SpaceNumber_data)) {
                                            System.out.println("Violation not Detected. \n");
                                        } else {
                                            System.out.println("Violation Detected \n");
                                            System.out.println("Enter Citation Number: ");
                                            String CitationNumber = input.nextLine();
                                            String VehicleLicensedel = VehicleLicense;
                                            String ParkingNamedel = ParkingName;
                                            String ZoneIDdel = ZoneID;
                                            String SpaceNumberdel = SpaceNumber;
                                            System.out.println("Enter Citation Date: ");
                                            String Date = input.nextLine();
                                            System.out.print("Enter Citation Time: ");
                                            String Time = input.nextLine();
                                            System.out.println("Enter Category: ");
                                            String Category = input.nextLine();
                                            System.out.println("Enter Space type: ");
                                            String tmpspacetype = input.nextLine();
                                            int Fee = citationFees.get(Category);
                                            Fee = tmpspacetype.equals("handicap") ? Fee / 2 : Fee;
                                            stmt.executeUpdate(
                                                    "INSERT INTO CitationInfo (CitationNumber, VehicleLicense, Date, Time, ParkingName, ZoneID, SpaceNumber, Category, Fee, Status)"
                                                            +
                                                            " VALUES ('" + CitationNumber + "' ,'" + VehicleLicensedel
                                                            + "' ,'" + Date + "' ,'" + Time + "' , '" + ParkingNamedel
                                                            + "' , '" + ZoneIDdel + "' , '" + SpaceNumberdel + "' , '"
                                                            + Category + "' , '$" + Fee + "' , 'DUE')");
                                            System.out.println("Citation generated successfully! \n");

                                        }
                                        break;

                                    case "2":
                                        inner = true;
                                        System.out.print("Enter Citation Number: ");
                                        String CitationNumber = input.nextLine();
                                        rs = stmt
                                                .executeQuery("SELECT Status FROM CitationInfo WHERE CitationNumber = '"
                                                        + CitationNumber + "'");
                                        String data_column1 = "";
                                        while (rs.next()) {
                                            data_column1 = rs.getString(1);

                                        }
                                        if (data_column1.equals("PAID")) {
                                            System.out.print("You have already paid the fee.\n");
                                        } else {
                                            stmt.executeUpdate(
                                                    "UPDATE CitationInfo SET Status='PAID' WHERE CitationNumber ='"
                                                            + CitationNumber + "'");
                                            System.out.print("You have successfully paid the fee.\n");
                                        }
                                        break;

                                    case "3":
                                        inner = true;
                                        System.out.print("Enter Appeal ID: ");
                                        String AppealID = input.nextLine();
                                        System.out.print("Enter Citation Number: ");
                                        String CitationID = input.nextLine();
                                        System.out.print("Enter Appeal Date: ");
                                        String AppealDate = input.nextLine();
                                        stmt.executeUpdate(
                                                "INSERT INTO CitationAppeal (AppealID, CitationID, AppealDate, AppealStatus) "
                                                        +
                                                        "VALUES (" + AppealID + " ,'" + CitationID + "' ,'" + AppealDate
                                                        + "' ,'Pending' )");
                                        System.out.println("Citation Appeal Information Updated! \n");
                                        break;

                                    default:
                                        System.out.println("Invalid Operation! Back to Main Menu. \n");
                                        inner = false;
                                        break;

                                }
                            } while (inner == true);
                            break;

                        case '4':
                            inner = true;
                            do {
                                System.out.println("Choose an operation:\n " +
                                        "1) Generate a report for the total number of citations given in all zones monthly\n"
                                        + "2) generate a report for the total number of citations given in all zones annually\n"
                                        + "3) Return the list of zones for each lot as tuple pairs (lot, zone)\n" +
                                        "4) Return the number of cars that are currently in violation\n"
                                        + "5) Return the number of employees having permits for a given parking zone\n"
                                        + "6)Return permit information given an ID or phone number\n" +
                                        "7) Return an available space number given a space type in a given parking lot.\n"
                                        + "8) Generate a report for the total number of citations given in all zones within time range\n");
                                operation = input.nextLine();
                                switch (operation) {
                                    case "1":
                                        inner = true;
                                        rs = stmt.executeQuery(
                                                "SELECT ParkingName, Count(*) AS TotalCitations FROM CitationInfo WHERE Date > now() - INTERVAL 30 day GROUP BY ParkingName");
                                        while (rs.next()) {
                                            String data_column1 = rs.getString(1);
                                            String data_column2 = rs.getString(2);
                                            System.out.println(data_column1);
                                            System.out.println(data_column2);
                                        }
                                        break;

                                    case "2":
                                        inner = true;
                                        rs = stmt.executeQuery(
                                                "SELECT ParkingName, Count(*) as TotalCitations FROM CitationInfo WHERE Date > now() - INTERVAL 365 day GROUP BY ParkingName");
                                        while (rs.next()) {
                                            String data_column1 = rs.getString(1);
                                            String data_column2 = rs.getString(2);
                                            System.out.println(data_column1);
                                            System.out.println(data_column2);
                                        }
                                        break;
                                    case "3":
                                        inner = true;
                                        rs = stmt.executeQuery(
                                                "Select DISTINCT Name, ZoneID from ParkingLotInformation");
                                        while (rs.next()) {
                                            String data_column1 = rs.getString(1);
                                            String data_column2 = rs.getString(2);
                                            System.out.println(data_column1);
                                            System.out.println(data_column2);
                                        }
                                        break;
                                    case "4":
                                        inner = true;
                                        System.out.print("Total number of cars in violation:");
                                        rs = stmt.executeQuery(
                                                "SELECT COUNT(*) AS CarsInViolation FROM CitationInfo WHERE Status ='DUE'");
                                        while (rs.next()) {
                                            data = rs.getString(1);
                                            System.out.println(data);
                                        }
                                        break;
                                    case "5":
                                        inner = true;
                                        System.out.print("Enter the ZoneId: ");
                                        String input_ZoneID = input.nextLine();
                                        rs = stmt.executeQuery(
                                                "SELECT p.ParkingName, COUNT(*) AS TotalEmployees FROM DriverInformation d INNER JOIN PermitInfo p"
                                                        +
                                                        " ON d.DriverID = p.DriverID WHERE d.Status ='E' AND p.ZoneID='"
                                                        + input_ZoneID + "' GROUP BY p.ParkingName");
                                        while (rs.next()) {
                                            String data_column1 = rs.getString(1);
                                            String data_column2 = rs.getString(2);
                                            System.out.println(data_column1);
                                            System.out.println(data_column2);
                                        }
                                        break;
                                    case "6":
                                        inner = true;
                                        System.out.print("Enter the Driver Id: ");
                                        String input_DriverID = input.nextLine();
                                        rs = stmt.executeQuery(
                                                "SELECT * FROM PermitInfo Where DriverID='" + input_DriverID + "'");
                                        while (rs.next()) {
                                            String data_column1 = rs.getString(1);
                                            String data_column2 = rs.getString(2);
                                            String data_column3 = rs.getString(3);
                                            String data_column4 = rs.getString(4);
                                            String data_column5 = rs.getString(5);
                                            String data_column6 = rs.getString(6);
                                            String data_column7 = rs.getString(7);
                                            String data_column8 = rs.getString(8);
                                            String data_column9 = rs.getString(9);
                                            String data_column10 = rs.getString(10);
                                            System.out.println("Permit Id: " + data_column1);
                                            System.out.println("ParkingName: " + data_column2);
                                            System.out.println("ZoneID: " + data_column3);
                                            System.out.println("SpaceNumber: " + data_column4);
                                            System.out.println("DriverID: " + data_column5);
                                            System.out.println("StartDate: " + data_column6);
                                            System.out.println("Exp Date: " + data_column7);
                                            System.out.println("ExpTime: " + data_column8);
                                            System.out.println("PermitType: " + data_column9);
                                            System.out.println("Vehicle License: " + data_column10);
                                        }
                                        break;
                                    case "7":
                                        inner = true;
                                        System.out.print("Enter the parking lot name:");
                                        String input_pname = input.nextLine();
                                        System.out.print("Enter the SpaceType:");
                                        String input_SpaceType = input.nextLine();
                                        rs = stmt.executeQuery("SELECT ZoneID, SpaceNumber FROM ParkingLotInformation" +
                                                " WHERE SpaceType='" + input_SpaceType + "' AND Name='" + input_pname
                                                + "' AND AvailabilityStatus = 'yes'");
                                        while (rs.next()) {
                                            String data_column1 = rs.getString(1);
                                            String data_column2 = rs.getString(2);
                                            System.out.println("Zone ID:" + data_column1);
                                            System.out.println("Space Number: " + data_column2);
                                        }
                                        break;

                                    case "8":
                                        inner = true;
                                        System.out.print("Enter start date:");
                                        String startdate = input.nextLine();
                                        System.out.print("Enter end date:");
                                        String enddate = input.nextLine();
                                        rs = stmt.executeQuery(
                                                "SELECT ParkingName, Count(*) AS TotalCitations FROM CitationInfo WHERE Date >'"
                                                        + startdate + "' and Date <'" + enddate
                                                        + "' GROUP BY ParkingName");
                                        while (rs.next()) {
                                            String data_column1 = rs.getString(1);
                                            String data_column2 = rs.getString(2);
                                            System.out.println(data_column1);
                                            System.out.println(data_column2);
                                        }
                                        break;

                                    default:
                                        System.out.println("Invalid Operation! Back to Main Menu. \n");
                                        inner = false;
                                        break;
                                }
                            } while (inner == true);
                            break;

                        default:
                            System.out.println("Invalid Task! Back to Main Menu. \n");
                            x = false;
                            break;
                    }

                } while (x);

            } finally {
                close(rs);
                close(stmt);
                close(conn);
            }
        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Throwable whatever) {
                System.out.println(whatever);
            }
        }
    }

    static void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Throwable whatever) {
                System.out.println(whatever);
            }
        }
    }

    static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Throwable whatever) {
                System.out.println(whatever);
            }
        }
    }
}
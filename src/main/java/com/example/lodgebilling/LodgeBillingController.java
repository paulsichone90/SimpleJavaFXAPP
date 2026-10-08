package com.example.lodgebilling;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LodgeBillingController {

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> provinceComboBox;

    @FXML
    private ComboBox<String> roomComboBox;

    @FXML
    private TextField nightsField;

    @FXML
    private Label totalLabel;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> provinceColumn;

    @FXML
    private TableColumn<Customer, String> roomColumn;

    @FXML
    private TableColumn<Customer, Integer> nightsColumn;

    @FXML
    private TableColumn<Customer, Double> billColumn;

    private final ObservableList<Customer> customers =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        provinceColumn.setCellValueFactory(
                new PropertyValueFactory<>("province")
        );

        roomColumn.setCellValueFactory(
                new PropertyValueFactory<>("roomType")
        );

        nightsColumn.setCellValueFactory(
                new PropertyValueFactory<>("nights")
        );

        billColumn.setCellValueFactory(
                new PropertyValueFactory<>("bill")
        );

        provinceComboBox.getItems().addAll(
                "Central",
                "Copperbelt",
                "Eastern",
                "Luapula",
                "Lusaka",
                "Muchinga",
                "Northern",
                "North-Western",
                "Southern",
                "Western"
        );

        roomComboBox.getItems().addAll(
                "Standard",
                "Deluxe",
                "Executive"
        );

        customerTable.setItems(customers);
    }

    @FXML
    private void handleAddCustomer() {

        String name = nameField.getText().trim();
        String province = provinceComboBox.getValue();
        String roomType = roomComboBox.getValue();
        String nightsText = nightsField.getText().trim();

        if (name.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Please enter the customer's name."
            );

            return;
        }

        if (province == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Please select a province."
            );

            return;
        }

        if (roomType == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Please select a room type."
            );

            return;
        }

        if (nightsText.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Please enter the number of nights."
            );

            return;
        }

        int nights;

        try {

            nights = Integer.parseInt(nightsText);

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Number of nights must be a number."
            );

            return;
        }

        if (nights <= 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Number of nights must be greater than zero."
            );

            return;
        }

        double pricePerNight = getRoomPrice(roomType);

        double totalBill = pricePerNight * nights;

        Customer customer = new Customer(
                name,
                province,
                roomType,
                nights,
                totalBill
        );

        customers.add(customer);

        clearFields();

        totalLabel.setText(
                String.format("Total: K%.2f", totalBill)
        );
    }

    private double getRoomPrice(String roomType) {

        return switch (roomType) {

            case "Standard" -> 500.00;

            case "Deluxe" -> 800.00;

            case "Executive" -> 1200.00;

            default -> 0.00;
        };
    }

    @FXML
    private void handleDeleteCustomer() {

        Customer selectedCustomer =
                customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "No Selection",
                    "Please select a customer to delete."
            );

            return;
        }

        Alert confirmation = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        confirmation.setTitle("Confirm Deletion");
        confirmation.setHeaderText("Delete Customer");
        confirmation.setContentText(
                "Are you sure you want to delete "
                        + selectedCustomer.getName() + "?"
        );

        confirmation.showAndWait().ifPresent(response -> {

            if (response.getText().equals("OK")) {

                customers.remove(selectedCustomer);

                totalLabel.setText("Total: K0.00");
            }
        });
    }

    @FXML
    private void handleLogout(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("Login.fxml")
            );

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage)
                    ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("LODGE BILLING LAB");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void clearFields() {

        nameField.clear();
        provinceComboBox.setValue(null);
        roomComboBox.setValue(null);
        nightsField.clear();
    }

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
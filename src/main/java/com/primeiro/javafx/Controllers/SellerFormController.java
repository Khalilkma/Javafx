package com.primeiro.javafx.Controllers;

import com.primeiro.javafx.db.DbException;
import com.primeiro.javafx.listeners.DataChangeListener;
import com.primeiro.javafx.model.entities.Seller;
import com.primeiro.javafx.model.exceptions.ValidationException;
import com.primeiro.javafx.model.services.SellerService;
import com.primeiro.javafx.util.Alerts;
import com.primeiro.javafx.util.Constraints;
import com.primeiro.javafx.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class SellerFormController implements Initializable {

   private Seller entity;

   private SellerService service;

   private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSave;

    @FXML
    private Button btCancel;

    public void setSeller(Seller entity) {
        this.entity = entity;
    }

    public void setSellerService(SellerService service) {
        this.service = service;
    }

    public void subscribeDataChengeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    @FXML
    public void onBtSaveAction(ActionEvent event) {
        if (entity == null) {
            throw new IllegalStateException("Entity was null");
        }
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        try {
            entity = getFormData();
            service.saveOrUpdate(entity);
            notifiDataChangeListeners();
            Utils.currentStage(event).close();
        }
        catch(ValidationException e) {
            setErrorMessages(e.getErrors());
        }
        catch(DbException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void notifiDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

    private Seller getFormData() {
        Seller obj = new Seller();

        ValidationException exception = new ValidationException("Validadion exception");

        obj.setId(Utils.tryParseToInt(txtId.getText()));


        if (txtName.getText() == null || txtName.getText().trim().equals("")) {
        exception.addError("name", "Field can't be empty");
        }
        obj.setName(txtName.getText());

        if(exception.getErrors().size() > 0) {
            throw exception;
        }

        return obj;
    }

    public void onBtCancelAction(ActionEvent event) {
        Utils.currentStage(event).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InitializeNodes();
    }

    private void InitializeNodes() {
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }

    public void updateFormData() {
        if(entity == null) {
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(entity.getId()));
        txtName.setText((entity.getName()));
    }

    private void setErrorMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("name")) {
            labelErrorName.setText(errors.get("name"));
        }
    }
}

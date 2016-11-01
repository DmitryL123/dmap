package ru.dz.vita2d.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.svg.SVGGlyphLoader;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@Controller
public class MainController implements Initializable {
	private static final int MINWIDTH = 1024;
	private static final int MINHEIGHT = 768;
	private static final String SIDEMENULOCATION = "/ru/dz/vita2d/views/sideMenu.fxml";
	private static final String CONTENTLOCATION = "/ru/dz/vita2d/views/content.fxml";
	private static final String ICOMOONFONTLOCATION = "/ru/dz/vita2dfonts/icomoon.svg";
	private Stage stage;
	StackPane stackPane = null;
	AnchorPane contentArea = null;

	@FXML
	private AnchorPane root;
	@FXML
	private StackPane menu;
	@FXML
	private StackPane titleBurgerContainer;
	@FXML
	private JFXHamburger titleBurger;
	@FXML
	private StackPane optionsBurger;
	@FXML
	private JFXRippler optionsRippler;
	@FXML
	private JFXDrawer drawer;
	@FXML
	private JFXPopup toolbarPopup;
	@FXML
	private Label exit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Platform.runLater(() -> {
			stage = (Stage) root.getScene().getWindow();
			stage.setMinWidth(MINWIDTH);
			stage.setMinHeight(MINHEIGHT);
		
		});

		// init the title hamburger
		drawer.setOnDrawerOpening((handler) -> {
			titleBurger.getAnimation().setRate(1);
			titleBurger.getAnimation().play();
		});

		drawer.setOnDrawerClosing((handler) -> {
			titleBurger.getAnimation().setRate(-1);
			titleBurger.getAnimation().play();
		});

		titleBurgerContainer.setOnMouseClicked((handler) -> {
			if (drawer.isHidden() || drawer.isHidding())
				drawer.open();
			else
				drawer.close();
		});

		// init the side menu
		try {
			stackPane = FXMLLoader.load(getClass().getResource(SIDEMENULOCATION));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		drawer.setSidePane(stackPane);

		// init Popup
		toolbarPopup.setPopupContainer(menu);
		toolbarPopup.setSource(optionsRippler);
		menu.getChildren().remove(toolbarPopup);

		optionsBurger.setOnMouseClicked((handler) -> {
			toolbarPopup.show(PopupVPosition.TOP, PopupHPosition.RIGHT, -12, 15);
		});

		
		// init content Area
		try {
			contentArea = FXMLLoader.load(getClass().getResource(CONTENTLOCATION));			
		} catch (IOException e) {
			e.printStackTrace();
		}

		root.getChildren().add(contentArea);
		

		// close application
		exit.setOnMouseClicked((handler) -> {
			Platform.exit();
		});

	}

	private void loadSVG() {
		new Thread(() -> {
			try {
				SVGGlyphLoader.loadGlyphsFont(MainController.class.getResourceAsStream(ICOMOONFONTLOCATION),
						"icomoon.svg");
				System.out.println("loadGlyphsFont true !!!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block

				System.out.println("loadGlyphsFont false !!!!");
				e.printStackTrace();
			}
		}).start();
	}

}
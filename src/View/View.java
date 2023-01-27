/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Game;
import Model.Category;
import Model.Player;
import Model.UV;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;


public class View extends Application {
	private Game controllerGame = null;

	Scene initPlayers, sceneGame;
	Stage primaryStage;
	BorderPane setPane = new BorderPane();
	Label lblGameErrorMessage = new Label();
	Button btnImprove = new Button("Improve my building");
	Label lblNextReward = new Label("Next Upgrade Reward: ");
	HBox hboxOtherPlayers = new HBox();
	Label lblCurrentPlayerName = new Label();
	ListView<Category> listCurrentPlayerCards = new ListView<Category>();
	ListView<UV> listCurrentPlayerResources = new ListView<UV>();
	Label lblCurrentAge = new Label("Current age: ");
	Label lblCurrentLap = new Label("Current lap: ");
	Label lblSensRotation = new Label();
	ListView<String> listRanking = new ListView<String>();
	Label lblBuildingName = new Label();
	Label lblBuildingUpgradeLevel = new Label("Upgrade Level: ");

	@Override
	public void start(Stage primaryStag) {
		this. primaryStage = primaryStag;

		Game.setUpSingleton(this);
		this.controllerGame = Game.getGameInstance();

		BorderPane paneInitPlayers = new BorderPane();
		paneInitPlayers.setId("paneInit");
		HBox hboxInitPlayers = new HBox();
		hboxInitPlayers.setId("hbox");
		Button btnAddPlayer = new Button("Add");
		Button btnDeletePlayer = new Button("Delete");
		TextField txtAddPlayer = new TextField();
		Button btnStartGame = new Button("Play");
		Button btnQuitGame = new Button("Quit");
		Label lblGameTitle = new Label("7Wonders");
		lblGameTitle.setId("title");
		Label lblInitPlayer = new Label("Please enter the names of the players");
		lblInitPlayer.setId("lblInitPlayers");
		ListView<String> listPlayers = new ListView<String>();
		VBox vboxAddPlayer = new VBox();
		vboxAddPlayer.setId("vbox");
		HBox hboxAddPlayer = new HBox();
		hboxAddPlayer.setId("hbox");
		HBox hboxBtnAddPlayer = new HBox();
		hboxBtnAddPlayer.setId("hbox");
		Label lblErrorMessage = new Label();

		HBox hboxButtonsGame = new HBox();
		hboxButtonsGame.setId("hbox");
		Button btnDiscard = new Button("Discard card");
		Button btnValidate = new Button("Validate category");
		lblBuildingUpgradeLevel.setId("lbl");

		hboxOtherPlayers.setId("hboxOtherPlayers");
		VBox vboxCurrentPlayerInfo = new VBox();
		vboxCurrentPlayerInfo.setId("vboxCurrentPlayer");
		HBox listsCurrentPlayer = new HBox();
		listsCurrentPlayer.setId("hbox");
		lblCurrentPlayerName.setId("labelPlayerName");
		VBox myCards = new VBox();
		myCards.setId("vbox");
		myCards.setMinWidth(650);

		VBox myResources = new VBox();
		myResources.setId("vbox");

		listCurrentPlayerCards.setId("listCards");
		listCurrentPlayerCards.setMinWidth(800);
		listCurrentPlayerResources.setId("listResources");
		listCurrentPlayerResources.setMinHeight(250);

		Label lblMyCards = new Label("My Cards:");
		lblMyCards.setId("lblMyBuilding");
		Label lblMyResources = new Label("My Building Resources:");
		lblMyResources.setId("lbl");

		lblCurrentLap.setId("lblGameInfo");
		lblCurrentAge.setId("lblGameInfo");
		lblSensRotation.setId("lblGameInfo");

		Label lblGameInfo = new Label("Game progress:");
		lblGameInfo.setId("gameInfotitle");
		listRanking.setId("rankingList");
		Label lblRating = new Label("Rating:");
//lblRanking.setId("gameInfotitle");
		VBox vboxGameInformation = new VBox();
		vboxGameInformation.setId("vboxGameInfo");

		VBox vboxBuilding = new VBox();
		lblGameErrorMessage.setId("GameErrorMessage");

		VBox vboxCenter = new VBox();

		try {
			paneInitPlayers.setTop(lblGameTitle);
			hboxAddPlayer.getChildren().addAll(lblInitPlayer, txtAddPlayer);
			hboxBtnAddPlayer.getChildren().addAll(btnAddPlayer, btnDeletePlayer);
			vboxAddPlayer.getChildren().addAll(hboxAddPlayer, hboxBtnAddPlayer, listPlayers, lblGameErrorMessage);
			paneInitPlayers.setCenter(vboxAddPlayer);
			hboxInitPlayers.getChildren().addAll(btnStartGame, btnQuitGame);
			paneInitPlayers.setBottom(hboxInitPlayers);
			this.initPlayers = new Scene(paneInitPlayers, 600, 600);
			initPlayers.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			btnStartGame.setOnAction(event ->{
				int numberPlayers = listPlayers.getItems().size();
				if(numberPlayers < 3 || numberPlayers > 7) {
					lblGameErrorMessage.setText("The game can accommodate 3 to 7 players!");
				} else {
					lblErrorMessage.setText("");
					controllerGame.setNumberPlayers(numberPlayers);
					for(String player: listPlayers.getItems()) {
						controllerGame.addPlayer(player);
					}
					controllerGame.setCurrentPlayer(controllerGame.getListPlayers().get(0));
					refreshDisplay();

					this.sceneGame = new Scene(setPane);
					sceneGame.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(sceneGame);
					primaryStage.setMaximized(true);
					primaryStage.setResizable(true);
				}
			});
			btnQuitGame.setOnAction(event -> {
				Platform.exit();
			});
			btnAddPlayer.setOnAction(event ->{
				if(txtAddPlayer.getText() != null && !txtAddPlayer.getText().isEmpty()) {
					if(listPlayers.getItems().contains(txtAddPlayer.getText())) {
						lblErrorMessage.setText("The name " + txtAddPlayer.getText() + " already exits!");
					} else {
						listPlayers.getItems().add(txtAddPlayer.getText());
						lblErrorMessage.setText("");
					}
					txtAddPlayer.clear();

				} else {
					lblGameErrorMessage.setText("Please enter the name of a player");
				}
			});
			btnDeletePlayer.setOnAction(event ->{
				final int selectedIdx = listPlayers.getSelectionModel().getSelectedIndex();
				try {
					listPlayers.getItems().remove(selectedIdx);
				}catch(Exception e) {
					lblGameErrorMessage.setText("Please select a player to delete");
				}
			});
			txtAddPlayer.setOnKeyReleased(event -> {
				if (event.getCode() == KeyCode.ENTER){
					if(txtAddPlayer.getText() != "") {
						if(listPlayers.getItems().contains(txtAddPlayer.getText())) {
							lblErrorMessage.setText("The name " + txtAddPlayer.getText() + " already exits!");
						} else {
							listPlayers.getItems().add(txtAddPlayer.getText());
							lblErrorMessage.setText("");
						}
						txtAddPlayer.clear();
					}
				}
			});
			btnDiscard.setOnAction(event ->{
				if(!listCurrentPlayerCards.getSelectionModel().isEmpty()) {
					clearResourcesTemp(controllerGame.getCurrentPlayer());
					final int selectedIdx = listCurrentPlayerCards.getSelectionModel().getSelectedIndex();
					controllerGame.getCurrentPlayer().discardMap(listCurrentPlayerCards.getItems().get(selectedIdx));
					controllerGame.passNextPlayer();
					refreshDisplay();
				} else {
					lblGameErrorMessage.setText("Please select a card to discard");
				}

			});
			btnImprove.setOnAction(event ->{
				if(!listCurrentPlayerCards.getSelectionModel().isEmpty()) {
					clearResourcesTemp(controllerGame.getCurrentPlayer());
					final int selectedIdx = listCurrentPlayerCards.getSelectionModel().getSelectedIndex();
					controllerGame.getCurrentPlayer().improveBuilding(listCurrentPlayerCards.getItems().get(selectedIdx));
					controllerGame.passNextPlayer();
					refreshDisplay();

				} else {
					lblGameErrorMessage.setText("Please select a card to upgrade your building");
				}
			});
			btnValidate.setOnAction(event ->{
				if(!listCurrentPlayerCards.getSelectionModel().isEmpty()) {
					final int selectedIdx = listCurrentPlayerCards.getSelectionModel().getSelectedIndex();
					boolean verif = controllerGame.getCurrentPlayer().validateCategory(listCurrentPlayerCards.getItems().get(selectedIdx));
					if(verif) {
						clearResourcesTemp(controllerGame.getCurrentPlayer());
						controllerGame.passNextPlayer();
						refreshDisplay();
					} else {
						lblGameErrorMessage.setText("You do not have sufficient resources to validate the category");
					}
				} else {
					lblGameErrorMessage.setText("Please select a card to validate");
				}

			});

			hboxButtonsGame.getChildren().addAll(btnDiscard, btnImprove, btnValidate);



			vboxGameInformation.getChildren().addAll(lblGameInfo, lblCurrentAge, lblCurrentLap, lblSensRotation, lblNextReward, listRanking);
			listRanking.setMinHeight(600);
			listRanking.setMinWidth(400);

			myCards.getChildren().addAll(lblMyCards, listCurrentPlayerCards);
			myCards.setId("vboxMapsBati");
			myResources.getChildren().addAll(lblMyResources,listCurrentPlayerResources);
			listsCurrentPlayer.getChildren().addAll(myCards, vboxBuilding);
			vboxBuilding.setId("vboxBuildingCards");
			vboxCurrentPlayerInfo.getChildren().addAll(lblCurrentPlayerName, listsCurrentPlayer,lblGameErrorMessage, hboxButtonsGame);
			vboxBuilding.getChildren().addAll(lblBuildingName, lblBuildingUpgradeLevel, lblNextReward, myResources);
			vboxCenter.getChildren().addAll(hboxOtherPlayers, vboxCurrentPlayerInfo);
			setPane.setRight(vboxGameInformation);
			setPane.setCenter(vboxCenter);
			primaryStage.setScene(initPlayers);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	ArrayList<UV> tempResourceList = new ArrayList<UV>();
	public void clearResourcesTemp(Player player) {
		if(tempResourceList.size() != 0) {
			for(UV uv: tempResourceList) {
				player.getOwnBuildingUTBM().getResources().remove(uv);
			}
			tempResourceList.clear();
		}
	}

	public void displayNewAge() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Age change");
		alert.setHeaderText("Now the current age is " + controllerGame.getCurrentAge());
		alert. show();


	}
	public void displayNewTower() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Turn change");
		alert.setHeaderText("Now the current turn is " + controllerGame.getCurrentPlayer());
		alert. showAndWait();

	}
	public void refreshDisplay() {
		lblGameErrorMessage.setText("");
		hboxOtherPlayers.getChildren().clear();
		for(Player player: controllerGame.getNeighborList()) {
			Label lblOtherName = new Label(player.getPlayerName());
			lblOtherName.setId("labelPlayerName");
			ListView<UV> listResources = new ListView<UV>();
			VBox vboxOther = new VBox();
			vboxOther.setId("vbox");
			Button btnPurchaseResourceNeighbor = new Button("Purchase UV");
			listResources.getItems().clear();
			listResources.getItems().addAll(controllerGame.getPlayerResources(player));
			vboxOther.getChildren().addAll(lblCurrentPlayerName, listResources,btnPurchaseResourceNeighbor);
			hboxOtherPlayers.getChildren().add(vboxOther);
			listResources.setId("listResources");

			btnPurchaseResourceNeighbor.setOnAction(event -> {
				if(!listResources.getSelectionModel().isEmpty()) {
					final int selectedIdx = listResources.getSelectionModel().getSelectedIndex();
					boolean verif = controllerGame.buyResourcesNeighbors(player, listResources.getItems().get(selectedIdx));
					if(verif) {
						refreshDisplay();
						lblGameErrorMessage.setText("UV bought");
						tempResourceList.add(listResources.getItems().get(selectedIdx));

					} else {
						lblGameErrorMessage.setText("You don't have enough silver coins to buy the UV");
					}
				} else {
					lblGameErrorMessage.setText("Please select a UV to purchase");
				}
			});
		}

		if(controllerGame.getCurrentPlayer().getOwnBuildingUTBM().getImprovementLevel() == 3) {
			btnImprove.setDisable(true);
			lblNextReward.setVisible(false);
		} else {
			btnImprove.setDisable(false);
			lblNextReward.setVisible(true);
		}
		lblCurrentPlayerName.setText("Current Player: " + controllerGame.getCurrentPlayer().getPlayerName() + " (Coins: " + controllerGame.getCurrentPlayer().getMoney() + ", ECTS Credits: " +
				controllerGame.getCurrentPlayer().getCreditsECTS() + ")");
		listCurrentPlayerCards.getItems().clear();
		listCurrentPlayerCards.getItems().addAll(controllerGame.getCurrentPlayer().getMainCards().getListCards());
		listCurrentPlayerResources.getItems().clear();

		listCurrentPlayerResources.getItems().addAll(controllerGame.getPlayerResources(controllerGame.getCurrentPlayer()));

		int age = controllerGame.getCurrentAge();
		String ageUTBM = "Trunk-Common";
		if(age == 2) {
			ageUTBM = "Branch";
		}else if(age == 3) {
			ageUTBM = "Specialization";
		}
		lblCurrentAge.setText("Current age: " + controllerGame.getCurrentAge() + " (" + ageUTBM + ")");



		lblCurrentLap.setText("Current lap: " + controllerGame.getCurrentPlayer());
		listRanking.getItems().clear();
		listRanking.getItems().addAll(controllerGame.getRanking());
		lblBuildingName.setText("My Building: " + controllerGame.getCurrentPlayer().getOwnBuildingUTBM().getBuildingNameEnum());
		lblBuildingName.setId("lblMyBuilding");
		lblBuildingUpgradeLevel.setText("Upgrade Level: " + controllerGame.getCurrentPlayer().getOwnBuildingUTBM().getImprovementLevel()+ "/3");
		lblNextReward.setId("lbl");
		if(controllerGame.getCurrentPlayer().getOwnBuildingUTBM().getImprovementLevel() != 3) {
			lblNextReward.setText("Next upgrade reward: " + controllerGame.getCurrentPlayer().getOwnBuildingUTBM().getBuildingImprovementGain());
		}
		if(controllerGame.isClockwiseExchange()) {
			lblSensRotation.setText("Direction: Clockwise");
		} else {
			lblSensRotation.setText("Sens: Anti-Clockwise");
		}


	}
	public void displayRanking() {
		setPane.setDisable(true);
		Button btnQuit = new Button("Quit");

		btnQuit.setOnAction(event ->{
			Platform.exit();
		});
		HBox hboxButtonsEnd = new HBox();
		hboxButtonsEnd.getChildren().addAll(btnQuit);

		Stage stageRating = new Stage();
		stageRating.initModality(Modality.APPLICATION_MODAL);
		stageRating.setMinWidth(700);
		stageRating.setMinHeight(700);
		ListView<String> playerrating = new ListView<String>();
		playerrating.setId("listRanking");
		playerrating.getItems().addAll(controllerGame.getRanking());
		BorderPane paneClassement = new BorderPane();
		VBox vboxInfos = new VBox();
		vboxInfos.setId("vbox");
		Label lblTitle = new Label("Final ranking");
		lblTitle.setId("title");

		vboxInfos.getChildren().addAll(lblTitle, playerrating, hboxButtonsEnd);
		paneClassement.setCenter(vboxInfos);

		Scene sceneRating = new Scene(paneClassement);
		sceneRating.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stageRating.setScene(sceneRating);
		stageRating.show();
	}

	public void endPart() {
		displayRanking();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
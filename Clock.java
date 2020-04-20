/*Alex Bowman 
 * Homework #8
 * Professor Silvestri
 * 4/19/20
 */
package animation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Clock extends Application {
	private ClockPane clock;
	private Timeline animation;

	@Override

	public void start(Stage primaryStage) {
		AppGUI ag = new AppGUI();
		Scene scene = new Scene(ag, 600, 600);
		primaryStage.setTitle("Clock In Action");
		primaryStage.setScene(scene);
		primaryStage.show();

		scene.widthProperty().addListener(e -> clock.setWidth(clock.getWidth()));
		scene.heightProperty().addListener(e -> clock.setHeight(clock.getHeight()));
	}

	public static void main(String[] args) {

		Application.launch(args);

	}

	private class AppGUI extends BorderPane {

		public AppGUI() {
			clock = new ClockPane();
			animation = new Timeline(new KeyFrame(Duration.millis(100), e -> clock.setCurrentTime()));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();

			ControlButtonsPane cbp = new ControlButtonsPane();
			this.setCenter(clock);
			this.setTop(cbp);
			
			ControlTextPane ctp = new ControlTextPane();
			this.setBottom(ctp);
		}
	}

	private class ControlButtonsPane extends HBox {

		public ControlButtonsPane() {

			this.setSpacing(10);
			this.setAlignment(Pos.CENTER);

			Button btPause = new Button("Stop");
			btPause.setOnAction(e -> animation.pause());
			
			Button btResume = new Button("Start");
			btResume.setOnAction(e -> animation.play());
			
			Button btLoadStart = new Button("Load Current Time & Start");
			btLoadStart.setOnAction(e -> clock.setCurrentTime());
			btLoadStart.setOnAction(e -> animation.play());
			
			Button btCurrent = new Button("Just Load Current Time");
			btCurrent.setOnAction(e -> clock.setCurrentTime());

			this.getChildren().addAll(btPause, btResume, btLoadStart, btCurrent);

		}

	}

	private class ControlTextPane extends HBox {

		public ControlTextPane() {

			this.setSpacing(10);
			this.setAlignment(Pos.CENTER);

			Label hours = new Label("Hours:");
			TextField hours1 = new TextField(clock.getHour() + "");
			hours1.setOnAction(e -> clock.setCurrentTime());
			hours1.setOnAction(e -> clock.setHour(Integer.parseInt(hours1.getText())));
			hours1.setPrefWidth(40);
			
			Label minutes = new Label("Minutes:");
			TextField minutes1 = new TextField(clock.getMinute() + "");
			minutes1.setOnAction(e -> clock.setMinute(Integer.parseInt(minutes1.getText())));
			minutes1.setPrefWidth(40);
			
			Label seconds = new Label("Seconds:");
			TextField seconds1 = new TextField(clock.getSecond() + "");
			seconds1.setOnAction(e -> clock.setSecond(Integer.parseInt(seconds1.getText())));
			seconds1.setPrefWidth(40);

			this.getChildren().addAll(hours, hours1, minutes, minutes1, seconds, seconds1);
		}
	}

}
package pl.pkrysztofiak.mvvm;

import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.pkrysztofiak.mvvm.model.Model;
import pl.pkrysztofiak.mvvm.model.panel.PanelModel;
import pl.pkrysztofiak.mvvm.view.View;
import pl.pkrysztofiak.mvvm.viewmodel.ViewModel;

public class App extends Application {
    
    private final ViewModel viewModel = new ViewModel();
    private final Model model = new Model(viewModel);
    
    private final View view = new View(viewModel);
    private final Scene scene = new Scene(view);
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(scene);
        stage.show();
        
        IntStream.range(0, 4).forEach(i -> {
            model.addPanelRequest.onNext(new PanelModel());
        });
    }
}
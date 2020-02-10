package pl.pkrysztofiak.mvvm.view.panel;

import io.reactivex.Observable;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import pl.pkrysztofiak.mvvm.viewmodel.panel.PanelViewModel;

public class PanelView extends StackPane {

    private final PanelViewModel panelViewModel;
    
    private final TextField nameTextField = new TextField();
    
    {
        setPrefSize(400, 100);
        getChildren().add(nameTextField);
    }
    
    public PanelView(PanelViewModel panelViewModel) {
        this.panelViewModel = panelViewModel;
        nameTextField.textProperty().bindBidirectional(panelViewModel.nameProperty());
    }
    
    
    public void setName(String value) {
        nameTextField.setText(value);
    }

    public Observable<String> nameObservable() {
        return panelViewModel.nameObservable();
    }
}
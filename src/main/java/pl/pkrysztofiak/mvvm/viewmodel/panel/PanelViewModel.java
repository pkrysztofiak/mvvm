package pl.pkrysztofiak.mvvm.viewmodel.panel;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.pkrysztofiak.mvvm.model.panel.PanelModel;

public class PanelViewModel {
    
    private final StringProperty nameProperty = new SimpleStringProperty();
    private final Observable<String> nameObservable = JavaFxObservable.valuesOf(nameProperty);

    public PanelViewModel(PanelModel panelModel) {
        
    }
    
    public StringProperty nameProperty() {
        return nameProperty;
    }
    
    public Observable<String> nameObservable() {
        return nameObservable;
    }
}
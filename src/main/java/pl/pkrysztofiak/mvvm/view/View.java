package pl.pkrysztofiak.mvvm.view;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import pl.pkrysztofiak.mvvm.view.panel.PanelView;
import pl.pkrysztofiak.mvvm.viewmodel.ViewModel;

public class View extends VBox {

    private final ObservableList<PanelView> panels = FXCollections.observableArrayList();
    
    public View(ViewModel viewModel) {
        Bindings.bindContent(panels, viewModel.getPanelViews());
        Bindings.bindContent(getChildren(), panels);
    }
}
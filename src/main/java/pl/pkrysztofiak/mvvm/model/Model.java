package pl.pkrysztofiak.mvvm.model;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.mvvm.model.panel.PanelModel;
import pl.pkrysztofiak.mvvm.viewmodel.ViewModel;
import pl.pkrysztofiak.mvvm.viewmodel.panel.PanelViewModel;

public class Model {

    private final ViewModel viewModel;
    
    private final ObservableList<PanelModel> panels = FXCollections.observableArrayList();
    private final Observable<PanelModel> panelAddedObservable = JavaFxObservable.additionsOf(panels);
    
    public final PublishSubject<PanelModel> addPanelRequest = PublishSubject.create();

    {
        addPanelRequest.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(this::onAddPanelRequest);
        
        panelAddedObservable.subscribe(this::onPanelAdded);
    }
    
    public Model(ViewModel viewModel) {
        this.viewModel = viewModel;
    }
    
    private void onAddPanelRequest(PanelModel panel) {
        panels.add(panel);
    }
    
    private void onPanelAdded(PanelModel panel) {
        PanelViewModel panelViewModel = new PanelViewModel(panel);
        viewModel.addPanelRequest.onNext(panelViewModel);
    }
}
package pl.pkrysztofiak.mvvm.viewmodel;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.subjects.PublishSubject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.mvvm.view.panel.PanelView;
import pl.pkrysztofiak.mvvm.viewmodel.panel.PanelViewModel;

public class ViewModel {
    
    private final ObservableList<PanelView> panelViews = FXCollections.observableArrayList();
    private final Observable<PanelView> panelViewAddedObservable = JavaFxObservable.additionsOf(panelViews);
    private final ObservableList<PanelView> unmodifiablePanelViews = FXCollections.unmodifiableObservableList(panelViews);

    public final PublishSubject<PanelViewModel> addPanelRequest = PublishSubject.create();
    
    {
        addPanelRequest.observeOn(JavaFxScheduler.platform()).subscribe(this::onAddPanelRequest);
    }
    
    public ViewModel() {
        panelViewAddedObservable.flatMap(PanelView::nameObservable).subscribe(name -> panelViews.forEach(panelView -> panelView.setName(name)));
    }
    
    private void onAddPanelRequest(PanelViewModel panelViewModel) {
        PanelView panelView = new PanelView(panelViewModel);
        panelViews.add(panelView);
    }
    
    public ObservableList<PanelView> getPanelViews() {
        return unmodifiablePanelViews;
    }
}
package gr.novi.androidunipiexample.viewmodels;

public interface MainViewModelListener {
    void success();
    void loading();
    void error(String description);
}

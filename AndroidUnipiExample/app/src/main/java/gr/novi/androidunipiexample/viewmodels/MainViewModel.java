package gr.novi.androidunipiexample.viewmodels;

import java.lang.ref.WeakReference;

public final class MainViewModel {
    private WeakReference<MainViewModelListener> mListener;

    public void handleCredentials(String username, String pass) {
        if (isCredentialsEmpty(username, pass)) {
            mListener.get().error("Empty fields");
            return;
        }
        if (containsSymbolsAt(username, pass)) {
            mListener.get().error("Special chars");
            return;
        }
        //TODO login request
        mListener.get().success();
    }

    public void setMainViewModelListener(MainViewModelListener listener) {
        mListener = new WeakReference<>(listener);
    }

    private Boolean isCredentialsEmpty(String username, String pass) {
        if (username.isEmpty()) return true;
        if (pass.isEmpty()) return true;
        return false;
    }

    private Boolean containsSymbolsAt(String username, String pass) {
        if (username.contains("%")) return true;
        if (pass.contains("%")) return true;
        return false;
    }
}

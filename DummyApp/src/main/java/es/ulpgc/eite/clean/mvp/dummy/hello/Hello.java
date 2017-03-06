package es.ulpgc.eite.clean.mvp.dummy.hello;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Hello {


    ///////////////////////////////////////////////////////////////////////////////////
    // State /////////////////////////////////////////////////////////////////////////

    interface HelloToBye {

        boolean isToolbarVisible();

        Context getManagedContext();
    }

    interface ToHello {
        void onScreenStarted();
        void setToolbarVisibility(boolean visible);
        void setTextVisibility(boolean visible);
        void setProgressBarVisibility(boolean visible);
    }

    interface HelloTo {
        Context getManagedContext();
        void destroyView();
        boolean isToolbarVisible();
        boolean isTextVisible();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // Screen ////////////////////////////////////////////////////////////////////////

    /**
     * Methods offered to VIEW to communicate with PRESENTER
     */
    interface ViewToPresenter extends Presenter<PresenterToView> {
        void onSayHelloBtnClicked();
        void onGoToByeBtnClicked();
        //void onStartingView();
    }

    /**
     * Required VIEW methods available to PRESENTER
     */
    interface PresenterToView extends ContextView {
        void finishScreen();
        void hideToolbar();

        void hideProgressBar();

        void showProgressBar();

        void hideHelloMsg();
        void showHelloMsg();
        void setHelloMsg(String txt);

        void setSayHelloBtnLabel(String txt);

        void setGoToByeBtnLabel(String txt);
    }

    /**
     * Methods offered to MODEL to communicate with PRESENTER
     */
    interface PresenterToModel extends Model<ModelToPresenter> {
        void onChangeMsgByHelloBtnClicked();

        String getHelloMsg();

        String getGoToByeBtnLabel();

        String getSayHelloBtnLabel();
    }

    /**
     * Required PRESENTER methods available to MODEL
     */
    interface ModelToPresenter {

    }
}

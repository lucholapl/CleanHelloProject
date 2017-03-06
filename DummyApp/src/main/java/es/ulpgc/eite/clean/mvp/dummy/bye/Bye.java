package es.ulpgc.eite.clean.mvp.dummy.bye;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Bye {


    ///////////////////////////////////////////////////////////////////////////////////
    // State /////////////////////////////////////////////////////////////////////////

    interface ByeToHello {

        boolean isToolbarVisible();

        Context getManagedContext();
    }

    interface ToBye {
        void onScreenStarted();
        void setToolbarVisibility(boolean visible);
        void setTextVisibility(boolean visible);
    }

    interface ByeTo {
        Context getManagedContext();
        void destroyView();
        boolean isToolbarVisible();
        boolean isTextVisible();
        //boolean checkButtonSayClicked();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // Screen ////////////////////////////////////////////////////////////////////////

    /**
     * Methods offered to VIEW to communicate with PRESENTER
     */
    interface ViewToPresenter extends Presenter<PresenterToView> {
        void onSayByeBtnClicked();
        void onGoToHelloBtnClicked();
       // boolean wasSayButtonClickedBefore();
    }

    /**
     * Required VIEW methods available to PRESENTER
     */
    interface PresenterToView extends ContextView {
        void finishScreen();
        void hideToolbar();
        void hideByeMsg();
        void showByeMsg();
        void setByeMsg(String txt);

        void setSayByeBtnLabel(String txt);

        void setGoToHelloBtnLabel(String txt);
    }

    /**
     * Methods offered to MODEL to communicate with PRESENTER
     */
    interface PresenterToModel extends Model<ModelToPresenter> {
        void onChangeMsgByBtnClicked();
        String getByeMsg();

        String getGoToHelloBtnLabel();

        String getSayByeBtnLabel();
    }

    /**
     * Required PRESENTER methods available to MODEL
     */
    interface ModelToPresenter {

    }
}

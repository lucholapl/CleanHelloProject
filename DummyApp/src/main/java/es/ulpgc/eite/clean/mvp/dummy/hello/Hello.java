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

    interface ToHello {
        void onScreenStarted();

        void setToolbarVisibility(boolean visible);

        void setTextVisibility(boolean visible);
    }

    interface HelloTo {
        Context getManagedContext();

        void destroyView();

        boolean isToolbarVisible();

        boolean isTextVisible();

        boolean checkButtonSayClicked();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // Screen ////////////////////////////////////////////////////////////////////////

    /**
     * Methods offered to VIEW to communicate with PRESENTER
     */
    interface ViewToPresenter extends Presenter<PresenterToView> {


        void onButtonHelloClicked();

        void onButtonGoToByeClicked();
    }

    /**
     * Required VIEW methods available to PRESENTER
     */
    interface PresenterToView extends ContextView {
        void finishScreen();

        void hideToolbar();

        void hideText();

        void showText();

        void setText(String txt);

        void setLabel(String txt);
    }

    /**
     * Methods offered to MODEL to communicate with PRESENTER
     */
    interface PresenterToModel extends Model<ModelToPresenter> {


        void onChangeMsgByHelloBtnClicked();

        String getHelloText();

        String getLabel();
    }

    /**
     * Required PRESENTER methods available to MODEL
     */
    interface ModelToPresenter {

    }
}

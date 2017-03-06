package es.ulpgc.eite.clean.mvp.dummy.bye;


import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.dummy.app.Mediator;
import es.ulpgc.eite.clean.mvp.dummy.app.Navigator;

public class ByePresenter extends GenericPresenter
        <Bye.PresenterToView, Bye.PresenterToModel, Bye.ModelToPresenter, ByeModel>
        implements Bye.ViewToPresenter, Bye.ModelToPresenter, Bye.ByeToHello, Bye.ToBye {


    private boolean toolbarVisible;
    private boolean buttonClicked;
    private boolean progressBarVisible;
    private boolean textVisible;

    /**
     * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
     * Responsible to initialize MODEL.
     * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
     * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
     *
     * @param view The current VIEW instance
     */
    @Override
    public void onCreate(Bye.PresenterToView view) {
        super.onCreate(ByeModel.class, this);
        setView(view);
        Log.d(TAG, "calling onCreate()");

        Log.d(TAG, "calling startingByeScreen()");
        Mediator app = (Mediator) getView().getApplication();
        app.startingByeScreen(this);
    }

    /**
     * Operation called by VIEW after its reconstruction.
     * Always call {@link GenericPresenter#setView(ContextView)}
     * to save the new instance of PresenterToView
     *
     * @param view The current VIEW instance
     */
    @Override
    public void onResume(Bye.PresenterToView view) {
        setView(view);
        Log.d(TAG, "calling onResume()");

        if (configurationChangeOccurred()) {
            onScreenStarted();

            if(textVisible){
                //getView().showHelloMsg();
                //getView().setHelloMsg(getModel().getHelloMsg());
                checkByeMsg();
            }
        }else { // segundo a primer plano

    }
    }

    /**
     * Helper method to inform Presenter that a onBackPressed event occurred
     * Called by {@link GenericActivity}
     */
    @Override
    public void onBackPressed() {
        Log.d(TAG, "calling onBackPressed()");
    }

    /**
     * Hook method called when the VIEW is being destroyed or
     * having its configuration changed.
     * Responsible to maintain MVP synchronized with Activity lifecycle.
     * Called by onDestroy methods of the VIEW layer, like: {@link GenericActivity#onDestroy()}
     *
     * @param isChangingConfiguration true: configuration changing & false: being destroyed
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        super.onDestroy(isChangingConfiguration);
        Log.d(TAG, "calling onDestroy()");
    }


    ///////////////////////////////////////////////////////////////////////////////////
    // View To Presenter /////////////////////////////////////////////////////////////

    @Override
    public void onSayByeBtnClicked() {
        Log.d(TAG, "calling onSayByeBtnClicked()");
        if (isViewRunning()) {
            buttonClicked = true;
            checkByeMsg();
            new CountDownTimer(3000, 1000) {

                public void onTick(long millisUntilFinished) {
                    progressBarVisible = true;
                    setTextVisibility(false);
                    checkProgressBarVisibility();
                    checkTextVisibility();
                }

                public void onFinish() {
                    progressBarVisible = false;
                    checkByeMsg();
                    setTextVisibility(true);
                    checkProgressBarVisibility();
                    checkTextVisibility();
                }
            }.start();
        }
    }

    @Override
    public void onGoToHelloBtnClicked() {
        Log.d(TAG, "calling onGoToHelloBtnClicked()");

            Navigator app = (Navigator) getView().getApplication();
            app.goToHelloScreen(this);

    }


    ///////////////////////////////////////////////////////////////////////////////////
    // To Bye //////////////////////////////////////////////////////////////////////

    @Override
    public void onScreenStarted() {
        Log.d(TAG, "calling onScreenStarted()");
        if (isViewRunning()) {
            getView().setGoToHelloBtnLabel(getModel().getGoToHelloBtnLabel());
            //String label = getModel().getGoToByeBtnLabel();
            //getView().setGoToHelloBtnLabel(label);
            getView().setSayByeBtnLabel(getModel().getSayByeBtnLabel());
            getView().hideByeMsg();

            if (!toolbarVisible) {
                getView().hideToolbar();
            }
        }
        checkProgressBarVisibility();
        checkTextVisibility();

    }

    @Override
    public void setToolbarVisibility(boolean visible) {
        toolbarVisible = visible;
    }

    @Override
    public void setTextVisibility(boolean visible) {
        textVisible = visible;
    }

    @Override
    public void setProgressBarVisibility(boolean visible) {
        progressBarVisible = visible;
    }


    ///////////////////////////////////////////////////////////////////////////////////
    // Bye To //////////////////////////////////////////////////////////////////////


    @Override
    public Context getManagedContext() {
        return getActivityContext();
    }

//    @Override
//    public void destroyView() {
//        if (isViewRunning()) {
//            getView().finishScreen();
//        }
//    }

    @Override
    public boolean isToolbarVisible() {
        return toolbarVisible;
    }

//    @Override
//    public boolean isTextVisible() {
//        return textVisible;
//    }


    ///////////////////////////////////////////////////////////////////////////////////
    private void checkByeMsg(){
        getView().showByeMsg();
        getView().setByeMsg(getModel().getByeMsg());
    }

    private void checkToolbarVisibility() {
        Log.d(TAG, "calling checkToolbarVisibility()");
        if (isViewRunning()) {
            if (!toolbarVisible) {
                getView().hideToolbar();
            }
        }
    }

    private void checkProgressBarVisibility() {
        Log.d(TAG, "calling checkProgressBarVisibility()");
        if(isViewRunning()) {
            if (!progressBarVisible) {
                getView().hideProgressBar();
            }else{
                getView().showProgressBar();
            }
        }
    }

    private void checkTextVisibility() {
        Log.d(TAG, "calling checkTextVisibility()");
        if (isViewRunning()) {
            if (!textVisible) {
                getView().hideByeMsg();
            } else {
                getView().showByeMsg();
            }
        }
    }

    public void startProgressBar() {
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
                getView().showProgressBar();
            }

            public void onFinish() {
                getView().hideProgressBar();
            }
        }.start();
    }

//
//    @Override
//    public boolean wasSayButtonClickedBefore() {
//        Mediator mediador = (Mediator) getApplication();
//        return mediador.checkButtonSayClicked();
//    }

}

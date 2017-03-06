package es.ulpgc.eite.clean.mvp.dummy.hello;


import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.dummy.app.Mediator;
import es.ulpgc.eite.clean.mvp.dummy.app.Navigator;

public class HelloPresenter extends GenericPresenter
        <Hello.PresenterToView, Hello.PresenterToModel, Hello.ModelToPresenter, HelloModel>
        implements Hello.ViewToPresenter, Hello.ModelToPresenter,  Hello.ToHello, Hello.HelloToBye {


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
    public void onCreate(Hello.PresenterToView view) {
        super.onCreate(HelloModel.class, this);
        setView(view);
        Log.d(TAG, "calling onCreate()");


        Log.d(TAG, "calling startingHelloScreen()");
        Mediator app = (Mediator) getView().getApplication();
        app.startingHelloScreen(this);


        //onStartingView();
    }

    /**
     * Operation called by VIEW after its reconstruction.
     * Always call {@link GenericPresenter#setView(ContextView)}
     * to save the new instance of PresenterToView
     *
     * @param view The current VIEW instance
     */
    @Override
    public void onResume(Hello.PresenterToView view) {
        setView(view);
        Log.d(TAG, "calling onResume()");

        if(configurationChangeOccurred()) { // giro pantalla
            onScreenStarted();

            if(textVisible){
                //getView().showHelloMsg();
                //getView().setHelloMsg(getModel().getHelloMsg());
                checkHelloMsg();
            }
        } else { // segundo a primer plano

        }

    /*
    if(configurationChangeOccurred()) {
      getView().setGoToByeBtnLabel(getModel().getSayHelloBtnLabel());

      checkToolbarVisibility();
      checkTextVisibility();

      if (buttonClicked) {
        getView().setHelloMsg(getModel().getHelloMsg());
      }
    }
    */

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
    public void onSayHelloBtnClicked() {
        Log.d(TAG, "calling onSayHelloBtnClicked()");
        if (isViewRunning()) {
            buttonClicked = true;
            checkHelloMsg();
            new CountDownTimer(3000, 1000) {

                public void onTick(long millisUntilFinished) {
                    progressBarVisible = true;
                    setTextVisibility(false);
                    checkProgressBarVisibility();
                    checkTextVisibility();
                }

                public void onFinish() {
                    progressBarVisible = false;
                    checkHelloMsg();
                    setTextVisibility(true);
                    checkProgressBarVisibility();
                    checkTextVisibility();
                }
            }.start();


//        if(isViewRunning()) {
//            //getView().showHelloMsg();
//            //getView().setHelloMsg(getModel().getHelloMsg());
//            checkHelloMsg();
//            setTextVisibility(true);
//        }

    /*
    if(isViewRunning()) {
      getModel().onChangeMsgByBtnClicked();
      getView().setHelloMsg(getModel().getHelloMsg());
      textVisible = true;
      buttonClicked = true;
    }
    checkTextVisibility();
    */
        }
    }
    @Override
    public void onGoToByeBtnClicked() {
        Navigator app = (Navigator) getView().getApplication();
        app.goToByeScreen(this);
    }

  /*
  @Override
  public void onStartingView() {
    if(isViewRunning()) {
      getView().setGoToByeBtnLabel(getModel().getGoToByeBtnLabel());
      //String label = getModel().getGoToByeBtnLabel();
      //getView().setGoToByeBtnLabel(label);
      getView().setSayHelloBtnLabel(getModel().getSayHelloBtnLabel());
      getView().hideHelloMsg();
    }
  }
  */


    ///////////////////////////////////////////////////////////////////////////////////
    // To Hello //////////////////////////////////////////////////////////////////////

    @Override
    public void onScreenStarted() {
        Log.d(TAG, "calling onScreenStarted()");

        //onStartingView();

        if(isViewRunning()) {
            getView().setGoToByeBtnLabel(getModel().getGoToByeBtnLabel());
            //String label = getModel().getGoToByeBtnLabel();
            //getView().setGoToByeBtnLabel(label);
            getView().setSayHelloBtnLabel(getModel().getSayHelloBtnLabel());
            getView().hideHelloMsg();

            if(!toolbarVisible) {
                getView().hideToolbar();
            }
        }
        checkProgressBarVisibility();
        checkTextVisibility();

    /*
    if(isViewRunning()) {
      getView().setGoToByeBtnLabel(getModel().getSayHelloBtnLabel());
    }
    checkToolbarVisibility();
    checkTextVisibility();
    */
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
    // Hello To //////////////////////////////////////////////////////////////////////

    @Override
    public boolean isToolbarVisible() {
        return toolbarVisible;
    }

    @Override
    public Context getManagedContext(){
        return getActivityContext();
    }

  /*


  @Override
  public void destroyView(){
    if(isViewRunning()) {
      getView().finishScreen();
    }
  }

  @Override
  public boolean isTextVisible() {
    return textVisible;
  }
  */

    ///////////////////////////////////////////////////////////////////////////////////

    private void checkHelloMsg(){
        getView().showHelloMsg();
        getView().setHelloMsg(getModel().getHelloMsg());
    }

    private void checkToolbarVisibility(){
        Log.d(TAG, "calling checkToolbarVisibility()");
        if(isViewRunning()) {
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

    private void checkTextVisibility(){
        Log.d(TAG, "calling checkTextVisibility()");
        if(isViewRunning()) {
            if(!textVisible) {
                getView().hideHelloMsg();
            } else {
                getView().showHelloMsg();
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

}

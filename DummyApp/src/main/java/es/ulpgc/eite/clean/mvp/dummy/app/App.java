package es.ulpgc.eite.clean.mvp.dummy.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import es.ulpgc.eite.clean.mvp.dummy.hello.Hello;
import es.ulpgc.eite.clean.mvp.dummy.hello.HelloView;
import es.ulpgc.eite.clean.mvp.dummy.bye.Bye;
import es.ulpgc.eite.clean.mvp.dummy.bye.ByeView;

public class App extends Application implements Mediator, Navigator {

    //private DummyState toDummyState, dummyToState;
    private HelloState toHelloState, helloToState,byeToHelloState;
    private ByeState toByeState, byeToState, helloToByeState;

    @Override
    public void onCreate() {
        super.onCreate();
        toHelloState = new HelloState();
        //toHelloState.toolbarVisibility = true;
        //toHelloState.textVisibility = true;
//TODO: cuando haga falta el Bye, activar esto:
        //toByeState = new ByeState();
        //toByeState.toolbarVisibility = true;
        //toByeState.textVisibility = true;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // Mediator //////////////////////////////////////////////////////////////////////
//
//  @Override
//  public void startingDummyScreen(Dummy.ToDummy presenter){
//    if(toDummyState != null) {
//      presenter.setToolbarVisibility(toDummyState.toolbarVisibility);
//      presenter.setTextVisibility(toDummyState.textVisibility);
//    }
//    presenter.onScreenStarted();
//  }

    @Override
    public void startingHelloScreen(Hello.ToHello presenter) {
        if (toHelloState != null) {
            presenter.setToolbarVisibility(toHelloState.toolbarVisibility);
            //presenter.setTextVisibility(toHelloState.textVisibility);
        }
        presenter.onScreenStarted();
    }

//TODO: cuando haga falta el Bye, activar esto:
    @Override
    public void startingByeScreen(Bye.ToBye presenter) {
        if (toByeState != null) {
            presenter.setToolbarVisibility(toByeState.toolbarVisibility);
            //presenter.setTextVisibility(toByeState.textVisibility);
        }
        presenter.onScreenStarted();
    }

//    @Override
//    public boolean checkButtonSayClicked() {
//        return byeToState.btnSayClicked;
//    }


    ///////////////////////////////////////////////////////////////////////////////////
    // Navigator /////////////////////////////////////////////////////////////////////


    @Override
    public void goToHelloScreen(Bye.ByeToHello presenter) {
        byeToHelloState = new HelloState();
        byeToHelloState.toolbarVisibility = presenter.isToolbarVisible();
        //helloToState.textVisibility = presenter.isTextVisible();
        //helloToState.btnSayClicked = presenter.checkButtonSayClicked();

        Context view = presenter.getManagedContext();
        if (view != null) {
            view.startActivity(new Intent(view, HelloView.class));
            //presenter.destroyView();
        }

    }

    @Override
    public void goToByeScreen(Hello.HelloToBye presenter) {
        helloToByeState = new ByeState();
        helloToByeState.toolbarVisibility = presenter.isToolbarVisible();
        //helloToByeState.textVisibility = presenter.isTextVisible();
        //helloToByeState.btnSayClicked = presenter.checkButtonSayClicked();

        Context view = presenter.getManagedContext();
        if (view != null) {
            view.startActivity(new Intent(view, ByeView.class));
            //presenter.destroyView();
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////
    // State /////////////////////////////////////////////////////////////////////////

    private class ByeState {
        boolean toolbarVisibility;
        boolean progressBarVisibility;
        //boolean textVisibility;
    }

    private class HelloState {
        boolean toolbarVisibility;
        boolean progressBarVisibility;
        //boolean textVisibility;
    }

//    private class HelloState {
//        boolean toolbarVisibility;
//        boolean textVisibility;
//        //boolean btnSayClicked;
//    }
//
//    private class ByeState {
//        boolean toolbarVisibility;
//        boolean textVisibility;
//        boolean btnSayClicked;
//    }
}

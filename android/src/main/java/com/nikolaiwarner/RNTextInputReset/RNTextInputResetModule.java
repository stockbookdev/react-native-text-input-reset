package com.nikolaiwarner.RNTextInputReset;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class RNTextInputResetModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNTextInputResetModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNTextInputReset";
  }

  @ReactMethod
  public void resetKeyboardInput(final int reactTagToReset) {
      UIManagerModule uiManager = getReactApplicationContext().getNativeModule(UIManagerModule.class);
      uiManager.addUIBlock(new UIBlock() {
          @Override
          public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
              InputMethodManager imm = (InputMethodManager) getReactApplicationContext().getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
              if (imm != null) {
                  View viewToReset = nativeViewHierarchyManager.resolveView(reactTagToReset);
                  imm.restartInput(viewToReset);
              }
          }
      });
  }
}
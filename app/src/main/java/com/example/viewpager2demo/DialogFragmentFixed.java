package com.example.viewpager2demo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public abstract class DialogFragmentFixed extends DialogFragment {
	public String TAG = null;

	public DialogFragmentFixed() {
		TAG = ((Object) this).getClass().getSimpleName();
	}

	public Context context;


	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.context =  getActivity();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, android.R.style.Theme_Translucent_NoTitleBar);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		if(getDialog() == null){
			//防止DialogFragment在某些特定机型下crash
			//http://stackoverflow.com/questions/12265611/dialogfragment-nullpointerexception-support-library
			setShowsDialog(false);
		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void show(FragmentManager manager, String tag) {
		try {
			FragmentTransaction ft = manager.beginTransaction();
			ft.add(this, tag);
			ft.commitAllowingStateLoss();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	public void dismiss(FragmentManager manager) {
		Fragment fragment = manager.findFragmentByTag(TAG);
		if (fragment != null) {
			try {
				DialogFragment df = (DialogFragment) fragment;
				df.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
				boolean isFragmentPopped = false;
				try {
					// true if fragment is in the stack and pop up successfully
					isFragmentPopped = manager.popBackStackImmediate(TAG, 0);
				} catch (Exception ep) {
					ep.printStackTrace();
					isFragmentPopped = false;
				}
				if (!isFragmentPopped) {
					try {
						FragmentTransaction ft = manager.beginTransaction();
						ft.remove(this);
						ft.commitAllowingStateLoss();
						manager.executePendingTransactions();
					} catch (Exception ee) {
						ee.printStackTrace();
						super.dismiss();
					}
				}
			}
		}
	}

}

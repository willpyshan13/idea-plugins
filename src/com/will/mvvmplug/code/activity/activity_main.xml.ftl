<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:binding="http://schemas.android.com/apk/res-auto">

    <data>
		<import type="android.view.View" />
        <variable
            name="viewModel"
            type="${packageName}.ui.viewmodel.${viewmodelName}"/>
    </data>

      <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </androidx.constraintlayout.widget.ConstraintLayout>

</layout>

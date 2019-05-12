package dvranjicic.taskie.ui.fragments

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import dvranjicic.taskie.R
import dvranjicic.taskie.ui.fragments.base.BaseFragment

class AboutAuthorFragment : BaseFragment() {
    override fun getLayoutResourceId() = R.layout.fragment_about_author

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val link = view.findViewById(R.id.githubLink) as TextView
        link.movementMethod = LinkMovementMethod.getInstance()
    }

    companion object {
        fun newInstance(): AboutAuthorFragment = AboutAuthorFragment()
    }
}
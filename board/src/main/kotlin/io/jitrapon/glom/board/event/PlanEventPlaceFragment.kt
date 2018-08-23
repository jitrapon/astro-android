package io.jitrapon.glom.board.event

import android.arch.lifecycle.Observer
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.SimpleItemAnimator
import android.view.View
import androidx.os.bundleOf
import io.jitrapon.glom.base.model.UiModel
import io.jitrapon.glom.base.ui.BaseFragment
import io.jitrapon.glom.base.ui.widget.recyclerview.VerticalSpaceItemDecoration
import io.jitrapon.glom.base.util.*
import io.jitrapon.glom.board.R
import kotlinx.android.synthetic.main.plan_event_place_fragment.*

/**
 * Screen that shows place polls of the event plan
 *
 * @author Jitrapon Tiachunpun
 */
class PlanEventPlaceFragment : BaseFragment() {

    private lateinit var viewModel: PlanEventViewModel

    companion object {

        const val DELAY_FIRST_LOAD = 200L

        @JvmStatic
        fun newInstance(isFirstVisible: Boolean): PlanEventPlaceFragment {
            return PlanEventPlaceFragment().apply {
                arguments = bundleOf("isFirstVisible" to isFirstVisible)
            }
        }
    }

    /**
     * Returns this fragment's XML layout
     */
    override fun getLayoutId(): Int = R.layout.plan_event_place_fragment

    /**
     * Create this fragment's ViewModel instance. The instance is reused from the one
     * instantiated with this fragment's activity
     */
    override fun onCreateViewModel(activity: FragmentActivity) {
        viewModel = obtainViewModel(PlanEventViewModel::class.java, activity)
    }

    /**
     * Called when any view must be initialized
     */
    override fun onSetupView(view: View) {
        event_plan_place_vote_progressbar.hide()
        event_plan_place_poll_recyclerview.apply {
            adapter = EventPollAdapter(viewModel, false)
            addItemDecoration(VerticalSpaceItemDecoration(context!!.dimen(R.dimen.event_plan_poll_vertical_offset)))
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
        event_plan_place_poll_status_button.setOnClickListener {
            viewModel.togglePlacePollStatus()
        }
        event_plan_place_select_poll_button.setOnClickListener {
            viewModel.setPlaceFromPoll()
        }

        // if this is the first page the user sees, load the plan immediately
        delayRun(DELAY_FIRST_LOAD) {
            arguments?.let {
                if (it.getBoolean("isFirstVisible", false)) {
                    viewModel.loadPlacePolls()
                }
            }
        }
    }

    /**
     * Called when this fragment is ready to subscribe to ViewModel's events
     */
    override fun onSubscribeToObservables() {
        viewModel.getObservablePlacePlan().observe(this@PlanEventPlaceFragment, Observer {
            it?.let {
                when (it.status) {
                    UiModel.Status.LOADING -> {
                        event_plan_place_vote_progressbar.show()
                    }
                    UiModel.Status.SUCCESS -> {
                        if (it.itemsChangedIndices.isNullOrEmpty()) {
                            event_plan_place_vote_progressbar.hide()
                            event_plan_place_poll_recyclerview.adapter.notifyDataSetChanged()
                        }
                        else {
                            for (index in it.itemsChangedIndices!!) {
                                event_plan_place_poll_recyclerview.adapter.notifyItemChanged(index)
                            }
                        }
                    }
                    else -> {
                        event_plan_place_vote_progressbar.hide()
                    }
                }
            }
        })

        viewModel.getObservablePlacePlan().observe(this@PlanEventPlaceFragment, Observer {
            it?.let {
                when (it.status) {
                    UiModel.Status.LOADING -> {
                        event_plan_place_vote_progressbar.show()
                    }
                    UiModel.Status.SUCCESS -> {
                        if (it.itemsChangedIndices.isNullOrEmpty()) {
                            event_plan_place_vote_progressbar.hide()
                            event_plan_place_poll_recyclerview.adapter.notifyDataSetChanged()
                        }
                        else {
                            for (index in it.itemsChangedIndices!!) {
                                event_plan_place_poll_recyclerview.adapter.notifyItemChanged(index)
                            }
                        }
                    }
                    else -> {
                        event_plan_place_vote_progressbar.hide()
                    }
                }
            }
        })
    }

    /**
     * Called when fragment is visible
     */
    fun onVisible() {
        delayRun(DELAY_FIRST_LOAD) {
            viewModel.loadPlacePolls()
        }
    }
}
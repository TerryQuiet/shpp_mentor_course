package tk.quietdev.level1.ui.pager.contacts.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tk.quietdev.level1.databinding.ListItemBinding
import tk.quietdev.level1.models.UserModel
import tk.quietdev.level1.utils.DiffCallBack
import tk.quietdev.level1.utils.ListState
import tk.quietdev.level1.utils.OnSwipeCallBack

open class ContactsAdapter(
    private val onClickListener: ContactHolder.OnItemClickListener,
    private val removeState: MutableLiveData<ListState>,
    private val itemStateChecker: ContactHolder.ItemStateChecker,
) : ListAdapter<UserModel, ContactHolder>(DiffCallBack), OnSwipeCallBack.Listener {


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val onSwipeCallBack = OnSwipeCallBack(this)
        ItemTouchHelper(onSwipeCallBack).attachToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        return ContactHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onClickListener,
            itemStateChecker
        )
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onHolderSwiped(viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as ContactHolder).remove()
    }

    override fun onViewAttachedToWindow(holder: ContactHolder) {
        super.onViewAttachedToWindow(holder)
        holder.setObserver(removeState)
    }

    override fun onViewDetachedFromWindow(holder: ContactHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.removeObserver(removeState)
    }


}

package com.example.pepulnowsearch.main.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pepulnowsearch.data.model.peopleclass.PeopleContactList
import com.example.pepulnowsearch.databinding.FragmentPeopleBinding
import com.example.pepulnowsearch.main.ui.adapter.PeopleContactAdapter
import com.example.pepulnowsearch.utils.enum_class.AccountTypeEnumClass
import com.example.pepulnowsearch.utils.enum_class.FollowStatusEnumClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment() {
    private lateinit var binding: FragmentPeopleBinding
    private val peopleArrayList: ArrayList<PeopleContactList> by lazy { arrayListOf() }
    private val peopleAdapter: PeopleContactAdapter by lazy { PeopleContactAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeopleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        addDataPeople()
        buttonStateChange()
    }

    private fun buttonStateChange() {
        peopleAdapter.dataPassForButtonStateChange { peopleContactList, buttonPosition ->
            Log.d("buttonStateChanges", peopleContactList.privateAccount.toString())
            if (peopleContactList.followStatusPeopleType == FollowStatusEnumClass.FOLLOW.ordinal) {
                if (peopleContactList.privateAccount == AccountTypeEnumClass.PRIVATE_ACCOUNT.ordinal) { //1 private Account
                    peopleContactList.followStatusPeopleType =
                        FollowStatusEnumClass.UNFOLLOW.ordinal
                    peopleAdapter.notifyItemChanged(buttonPosition)
                } else {                                            //0 un_private account
                    peopleContactList.followStatusPeopleType = FollowStatusEnumClass.REQUEST.ordinal
                    peopleAdapter.notifyItemChanged(buttonPosition)
                }
            } else if (peopleContactList.followStatusPeopleType == FollowStatusEnumClass.UNFOLLOW.ordinal) {
                peopleContactList.followStatusPeopleType = FollowStatusEnumClass.FOLLOW.ordinal
                peopleAdapter.notifyItemChanged(buttonPosition)
            } else if (peopleContactList.followStatusPeopleType == FollowStatusEnumClass.REQUEST.ordinal) {
                peopleContactList.followStatusPeopleType = FollowStatusEnumClass.FOLLOW.ordinal
                peopleAdapter.notifyItemChanged(buttonPosition)
            } else {
            }
        }
    }

    private fun addDataPeople() {
        peopleArrayList.add(
            PeopleContactList(
                1,
                "Mohan", 1,
                "30 Mutual Friends",
                0,
                "https://img.freepik.com/free-photo/young-bearded-man-with-striped-shirt_273609-5677.jpg"
            )
        )
        peopleArrayList.add(
            PeopleContactList(
                2,
                "Kannan", 1,
                "32 Mutual Friends",
                1,
                "https://img.freepik.com/free-photo/young-bearded-man-with-striped-shirt_273609-5677.jpg"
            )
        )
        peopleArrayList.add(
            PeopleContactList(
                3,
                "Hari", 0,
                "3 Mutual Friends",
                2,
                "https://img.freepik.com/free-photo/young-bearded-man-with-striped-shirt_273609-5677.jpg"
            )
        )
        peopleArrayList.add(
            PeopleContactList(
                4,
                "John", 0,
                "11 Mutual Friends",
                0,
                "https://img.freepik.com/free-photo/young-bearded-man-with-striped-shirt_273609-5677.jpg"
            )
        )
        peopleArrayList.add(
            PeopleContactList(
                5,
                "Naveen", 0,
                "2 Mutual Friends",
                1,
                "https://img.freepik.com/free-photo/young-bearded-man-with-striped-shirt_273609-5677.jpg"
            )
        )
        peopleArrayList.add(
            PeopleContactList(
                6,
                "Jayam", 0,
                "9 Mutual Friends",
                0,
                "https://img.freepik.com/free-photo/young-bearded-man-with-striped-shirt_273609-5677.jpg"
            )
        )
        peopleArrayList.add(
            PeopleContactList(
                7,
                "Kathir", 1,
                "4 Mutual Friends",
                2,
                "https://img.freepik.com/free-photo/young-bearded-man-with-striped-shirt_273609-5677.jpg"
            )
        )
    }

    private fun setAdapter() {
        peopleAdapter.differ.submitList(peopleArrayList)
        binding.rvPeopleRecycle.adapter = peopleAdapter
        binding.rvPeopleRecycle.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}
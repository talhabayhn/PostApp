package com.example.postapp.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.postapp.databinding.BottomSheetEditPostBinding
import com.example.postapp.ui.postlist.PostListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class EditPostBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetEditPostBinding? = null
    private val binding get() = _binding!!

    private val args: EditPostBottomSheetArgs by navArgs()

    private val viewModel: PostListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetEditPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefillFields()
        setupClickListeners()
    }

    private fun prefillFields() {
        binding.inputLayoutTitle.editText?.setText(args.postTitle)
        binding.inputLayoutBody.editText?.setText(args.postBody)
    }

    private fun setupClickListeners() {
        binding.buttonSave.setOnClickListener { attemptSave() }
        binding.buttonCancel.setOnClickListener { dismiss() }
    }

    private fun attemptSave() {
        val title = binding.inputLayoutTitle.editText?.text?.toString()?.trim().orEmpty()
        val body = binding.inputLayoutBody.editText?.text?.toString()?.trim().orEmpty()

        val isTitleValid = title.isNotEmpty()
        val isBodyValid = body.isNotEmpty()

        binding.inputLayoutTitle.error = if (isTitleValid) null else "Title cannot be empty"
        binding.inputLayoutBody.error = if (isBodyValid) null else "Body cannot be empty"

        if (isTitleValid && isBodyValid) {
            viewModel.updatePost(args.postId, title, body)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

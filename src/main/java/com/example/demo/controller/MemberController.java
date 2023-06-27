package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j    // Simple Logging Facade for Java

public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping("/member/list")
	public ModelAndView memberList() {

		ModelAndView mv=new ModelAndView("/member/list");
		List<MemberDTO> list=memberService.findMemberList();
		mv.addObject("list", list);
		return mv;
	}
	@RequestMapping("/member/detail")
	public ModelAndView  memberDetail(
			@RequestParam String id
	) {

		ModelAndView mv=new ModelAndView("/member/detail");
		MemberDTO member=memberService.findMemberDetail(id);
		mv.addObject("member", member);
		return mv;
	}
	@RequestMapping("/member/detail/{id}")
	public ModelAndView memberDetail2(
			@PathVariable String id
	) {

		ModelAndView mv=new ModelAndView("/member/detail");
		MemberDTO member=memberService.findMemberDetail(id);
		mv.addObject("member", member);
		return mv;
	}

	@RequestMapping("/member/add-form")
	public ModelAndView addform() {

		ModelAndView mv=new ModelAndView("/member/addform");
		return mv;
	}

	@RequestMapping("/member/member")
	public ModelAndView registerMember(
			MemberDTO dto
	) {

		ModelAndView mv=new ModelAndView("redirect:/member/list");
		memberService.registerMember(dto);
		return mv;
	}
	@RequestMapping("/member/remove")
	public ModelAndView removeMember(
			@RequestParam  String id
	) {
		log.info("========================== MemberController(/member/remove) ==================================");

		memberService.removeMember(id);
		ModelAndView mv=new ModelAndView("redirect:/member/list");
		return mv;
	}
}

/*************************************************************************
 ***                                                                   ***
 *** EE 526 L Experiment #9                     David_Lo, Spring, 2016 ***
 ***                                                                   ***
 *** Modeling a Sequence Controller                                    ***
 ***                                                                   ***
 *************************************************************************
 *** Filename: phasor.sv                Created by David_Lo, 4/30/2016 ***
 ***                                                                   ***
 *************************************************************************/

`timescale 1ns/1ns

module phasor(CLK, RSTN, EN, PHASE);
	input wire CLK, RSTN, EN;
	//output reg [1:0] PHASE;
	
	output enum bit [1:0] {FETCH = 0, DECODE = 1, EXECUTE = 2, UPDATE = 3} PHASE;
	
	always @(posedge CLK or negedge RSTN)
	begin
		if(!RSTN)
		begin
			PHASE <= FETCH;
		end
		else if(EN & PHASE == FETCH)
			PHASE = DECODE;
		else if(EN & PHASE == DECODE)
		   PHASE = EXECUTE;
		else if(EN & PHASE == EXECUTE)
		  PHASE = UPDATE;
		else if(EN & PHASE == UPDATE)
		  PHASE = FETCH;
		else
		  PHASE = PHASE;
	end
	
endmodule
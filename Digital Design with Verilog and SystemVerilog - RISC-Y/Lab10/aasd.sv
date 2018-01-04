`timescale 1ns/1ns

module aasd(RSTN, CLK, SYNC);
	input RSTN, CLK;
	output reg SYNC;
	reg ORIG;
	
	always @(posedge CLK or negedge RSTN)
	begin
		if(!RSTN) 
		begin
			ORIG <= 1'b0;
			SYNC <= 1'b0;
		end
		else
		begin
			ORIG <= 1'b1;
			SYNC <= ORIG;
		end
	
	end
	
endmodule
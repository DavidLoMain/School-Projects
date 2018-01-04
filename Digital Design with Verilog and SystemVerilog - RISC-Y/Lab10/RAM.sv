`timescale 1ns/1ns

module RAM(ADDR, RAM_OE, WS, RAM_CS, DATA);
	parameter WIDTH = 8;
	parameter DEPTH = 32;
	
	input [DEPTH - 1:0] ADDR;
	input RAM_OE, WS, RAM_CS;
	
	inout [WIDTH - 1:0] DATA;

	reg [WIDTH - 1:0] MEMORY [DEPTH - 1:0];
	reg [WIDTH - 1:0] DATA;
	
	always @(posedge WS)
	begin
		if(RAM_OE)
			MEMORY[ADDR] <= DATA;
	end

	// Chip Select must be low to read write. Else Data is high impedence
	assign DATA = (RAM_CS) ? MEMORY[ADDR]: 8'bz;
	
endmodule
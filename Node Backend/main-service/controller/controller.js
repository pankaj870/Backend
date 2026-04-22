function extractBaseEbookText(input) {
  // 1) Check that both markers [TXT-001] and [TXT-006] exist
  const hasMarkers = /(?=.*\[TXT-001\])(?=.*\[TXT-006\]).*/s.test(input);
  if (!hasMarkers) return null;

  // 2) Capture everything after "Summarize..." up to "Return the output..."
  const summarizeBlockMatch = input.match(
    /Summarize[\s\S]*?\n+([\s\S]*?)\s*(?:Return\s+the\s+output|$)/i
  );
  if (!summarizeBlockMatch) return null;

  // 3) Clean and pick the last non-empty meaningful line
  const captured = summarizeBlockMatch[1]
    .split(/\n+/)
    .map((s) => s.trim())
    .filter(Boolean);

  if (captured.length === 0) return null;

  return captured[captured.length - 1];
}

// Example usage:
const input = `

Prepend this wrapper to the base prompt. Output must be a JSON array in the following order:
  [
  { "level": "early_reader", "text": "string" },
  { "level": "middle_school", "text": "string" },
  { "level": "high_school", "text": "string" },
  { "level": "college_adult", "text": "string" }
  ]
  
  Rules:
  - Enforce exact key names and output order.
  - Reject missing or empty levels.
  - Ensure tone and complexity match level.
  - Maintain factual consistency across levels.
  - Retry once if schema fails, else use college_adult version.
 
 This Common Text enforces JSON contract and localization hygiene.
  - Output JSON only, no prose or fences.
  - Keys must match schema; escape characters correctly.
  - Use locale-neutral numerics; always specify units.
  - Include a BCP-47 lang tag when localization is required.
 
 Prepend Common Text [TXT-001] reader_level before executing this prompt logic.
  Prepend Common Text [TXT-006] json_contract_localization before executing this prompt logic.
  
  Summarize the core themes, storyline, and unique attributes of base eBook. Write clearly for prospective readers without revealing spoilers. Put in one paragraph with complete sentences without headers. Shown on list of MeBooks and details pages on retail site.
  
  Return the output in the following format:
  json
  structured JSON output depending on prompt logic
  
`;

console.log(extractBaseEbookText(input));
// 👉 Output: "Put in one paragraph with complete sentences without headers. Shown on list of MeBooks and details pages on retail site."
